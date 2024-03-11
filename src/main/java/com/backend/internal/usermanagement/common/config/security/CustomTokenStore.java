package com.backend.internal.usermanagement.common.config.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import com.backend.internal.usermanagement.repository.primary.AccessTokenRepository;
import com.backend.internal.usermanagement.common.util.SerializableObjectConverter;
import com.backend.internal.usermanagement.entity.primary.AccessTokenEntity;
import com.backend.internal.usermanagement.entity.primary.RefreshTokenEntity;
import com.backend.internal.usermanagement.entity.primary.UserEntity;
import com.backend.internal.usermanagement.repository.primary.RefreshTokenRepository;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Bunthoeurn CHUOB
 */
public class CustomTokenStore implements TokenStore {

    private static final Logger logger = LogManager.getLogger(CustomTokenStore.class);

    private AccessTokenRepository cbAccessTokenRepository;

    private RefreshTokenRepository cbRefreshTokenRepository;

    public CustomTokenStore(AccessTokenRepository cbAccessTokenRepository,
            RefreshTokenRepository cbRefreshTokenRepository) {
        this.cbAccessTokenRepository = cbAccessTokenRepository;
        this.cbRefreshTokenRepository = cbRefreshTokenRepository;
    }

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken accessToken) {
        return readAuthentication(accessToken.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        Optional<AccessTokenEntity> accessToken = cbAccessTokenRepository.findByTokenId(extractTokenKey(token));
        if (accessToken.isPresent()) {
            return accessToken.get().getAuthentication();
        }
        return null;
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String refreshToken = null;
        if (accessToken.getRefreshToken() != null) {
            refreshToken = accessToken.getRefreshToken().getValue();
        }

        if (readAccessToken(accessToken.getValue()) != null) {
            this.removeAccessToken(accessToken);
        }

        logger.info("Login {}", authentication.getPrincipal());

        UserEntity user = (UserEntity) authentication.getPrincipal();

        AccessTokenEntity cat = new AccessTokenEntity();
        cat.setTokenId(extractTokenKey(accessToken.getValue()));
        cat.setToken(SerializableObjectConverter.serializeAccessToken(accessToken));
        cat.setAuthenticationId(authenticationKeyGenerator.extractKey(authentication));
        cat.setUsername(authentication.isClientOnly() ? null : user.getUsername());
        cat.setUserId(authentication.isClientOnly() ? null : user.getId());
        cat.setClientId(authentication.getOAuth2Request().getClientId());
        cat.setAuthentication(authentication);
        cat.setExpiredAt(accessToken.getExpiration());
        cat.setRefreshToken(extractTokenKey(refreshToken));

        cbAccessTokenRepository.save(cat);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        Optional<AccessTokenEntity> accessToken = cbAccessTokenRepository.findByTokenId(extractTokenKey(tokenValue));
        if (accessToken.isPresent()) {
            return SerializableObjectConverter.deserializeAccessToken(accessToken.get().getToken());
        }
        return null;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken oAuth2AccessToken) {
        Optional<AccessTokenEntity> accessToken = cbAccessTokenRepository
                .findByTokenId(extractTokenKey(oAuth2AccessToken.getValue()));
        if (accessToken.isPresent()) {
            cbAccessTokenRepository.delete(accessToken.get());
        }
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        RefreshTokenEntity crt = new RefreshTokenEntity();
        crt.setTokenId(extractTokenKey(refreshToken.getValue()));
        crt.setToken(SerializableObjectConverter.serializeRefreshToken(refreshToken));
        crt.setAuthentication(authentication);
        cbRefreshTokenRepository.save(crt);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        Optional<RefreshTokenEntity> refreshToken = cbRefreshTokenRepository.findByTokenId(extractTokenKey(tokenValue));
        return refreshToken.isPresent()
                ? SerializableObjectConverter.deserializeRefreshToken(refreshToken.get().getToken())
                : null;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken refreshToken) {
        Optional<RefreshTokenEntity> rtk = cbRefreshTokenRepository
                .findByTokenId(extractTokenKey(refreshToken.getValue()));
        return rtk.isPresent() ? rtk.get().getAuthentication() : null;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken refreshToken) {
        Optional<RefreshTokenEntity> rtk = cbRefreshTokenRepository
                .findByTokenId(extractTokenKey(refreshToken.getValue()));
        if (rtk.isPresent()) {
            cbRefreshTokenRepository.delete(rtk.get());
        }
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        Optional<AccessTokenEntity> token = cbAccessTokenRepository
                .findByRefreshToken(extractTokenKey(refreshToken.getValue()));
        if (token.isPresent()) {
            cbAccessTokenRepository.delete(token.get());
        }
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        OAuth2AccessToken accessToken = null;
        String authenticationId = authenticationKeyGenerator.extractKey(authentication);
        Optional<AccessTokenEntity> token = cbAccessTokenRepository.findByAuthenticationId(authenticationId);

        if (token.isPresent()) {
            accessToken = SerializableObjectConverter.deserializeAccessToken(token.get().getToken());
            if (accessToken != null && !authenticationId
                    .equals(this.authenticationKeyGenerator.extractKey(this.readAuthentication(accessToken)))) {
                this.removeAccessToken(accessToken);
                this.storeAccessToken(accessToken, authentication);
            }
        }
        return accessToken;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        Collection<OAuth2AccessToken> tokens = new ArrayList<>();
        List<AccessTokenEntity> result = cbAccessTokenRepository.findByClientIdAndUsername(clientId, userName);
        result.forEach(e -> tokens.add(SerializableObjectConverter.deserializeAccessToken(e.getToken())));
        return tokens;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        Collection<OAuth2AccessToken> tokens = new ArrayList<>();
        List<AccessTokenEntity> result = cbAccessTokenRepository.findByClientId(clientId);
        result.forEach(e -> tokens.add(SerializableObjectConverter.deserializeAccessToken(e.getToken())));
        return tokens;
    }

    private String extractTokenKey(String value) {
        if (value == null) {
            return null;
        } else {
            MessageDigest digest;
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException var5) {
                throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
            }

            try {
                byte[] e = digest.digest(value.getBytes("UTF-8"));
                return String.format("%032x", new Object[] { new BigInteger(1, e) });
            } catch (UnsupportedEncodingException var4) {
                throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
            }
        }
    }
}
