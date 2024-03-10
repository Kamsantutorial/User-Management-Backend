package com.backend.internal.usermanagement.repository.primary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.internal.usermanagement.entity.primary.AccessTokenEntity;
import com.backend.internal.usermanagement.repository.base.BaseRepository;

@Repository
public interface AccessTokenRepository extends BaseRepository<AccessTokenEntity, Long> {

    List<AccessTokenEntity> findByClientId(@Param("clientId") String clientId);

    List<AccessTokenEntity> findByClientIdAndUsername(@Param("clientId") String clientId, @Param("username") String username);

    Optional<AccessTokenEntity> findByTokenId(@Param("tokenId") String tokenId);

    Optional<AccessTokenEntity> findByRefreshToken(@Param("refreshToken") String refreshToken);

    Optional<AccessTokenEntity> findByAuthenticationId(@Param("authenticationId") String authenticationId);
}
