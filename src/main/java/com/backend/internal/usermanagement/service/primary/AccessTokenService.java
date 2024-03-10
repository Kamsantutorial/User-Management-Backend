package com.backend.internal.usermanagement.service.primary;

public interface AccessTokenService {
    void removeAccessTokenByRoleId(Long roleId);

    void removeAccessTokenByUserId(Long userId);
}
