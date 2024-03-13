package com.backend.internal.usermanagement.entity.primary;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import com.backend.internal.usermanagement.common.util.SerializableObjectConverter;
import com.backend.internal.usermanagement.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "oauth_refresh_token")
public class RefreshTokenEntity extends BaseEntity<Long> {

    @Column(name = "token_id")
    private String tokenId;
    private String token;
    private String authentication;
    @Column(name = "expired_at")
    private Date expiredAt;

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
