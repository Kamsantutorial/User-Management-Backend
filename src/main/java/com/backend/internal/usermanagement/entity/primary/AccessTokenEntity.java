package com.backend.internal.usermanagement.entity.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.backend.internal.usermanagement.common.util.SerializableObjectConverter;
import com.backend.internal.usermanagement.entity.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "oauth_access_token")
public class AccessTokenEntity extends BaseEntity<Long> {

    @Column(name = "token_id")
    private String tokenId;
    private String token;
    @Column(name = "authentication_id")
    private String authenticationId;
    @Column(name = "user_name")
    private String username;
    @Column(name = "client_id")
    private String clientId;
    private String authentication;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity user;

    public OAuth2Authentication getAuthentication() {
        return SerializableObjectConverter.deserialize(authentication);
    }

    public void setAuthentication(OAuth2Authentication authentication) {
        this.authentication = SerializableObjectConverter.serialize(authentication);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
