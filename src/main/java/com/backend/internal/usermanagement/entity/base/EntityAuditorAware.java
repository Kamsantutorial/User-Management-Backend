package com.backend.internal.usermanagement.entity.base;

import java.util.Objects;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.backend.internal.usermanagement.entity.primary.UserEntity;

import lombok.NonNull;

/**
 * implement to get User login
 * curently just a mock data
 */
public class EntityAuditorAware implements AuditorAware<String> {

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            UserEntity user = (UserEntity) authentication.getPrincipal();
            if (Objects.nonNull(user))
                return Optional.of(String.valueOf(user.getId()));
            else
                return Optional.of("anonymous");
        } catch (Exception e) {
            return Optional.of("anonymous");
        }
    }
}
