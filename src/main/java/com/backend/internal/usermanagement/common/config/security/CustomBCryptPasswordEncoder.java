package com.backend.internal.usermanagement.common.config.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Bunthoeurn CHUOB
 */
@Component
public class CustomBCryptPasswordEncoder extends BCryptPasswordEncoder {

    public CustomBCryptPasswordEncoder() {
        super();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return super.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return super.matches(rawPassword, encodedPassword);
    }
}
