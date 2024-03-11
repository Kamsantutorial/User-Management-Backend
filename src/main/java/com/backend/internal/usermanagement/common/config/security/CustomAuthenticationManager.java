package com.backend.internal.usermanagement.common.config.security;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.backend.internal.usermanagement.dto.user.UserDTO;
import com.backend.internal.usermanagement.entity.primary.UserEntity;
import com.backend.internal.usermanagement.exception.ServerException;
import com.backend.internal.usermanagement.mapper.UserMapper;
import com.backend.internal.usermanagement.service.primary.UserService;

public class CustomAuthenticationManager implements AuthenticationManager {

    private static final Logger logger = LogManager.getLogger(CustomAuthenticationManager.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationManager(UserService userService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserEntity user = userService.findByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("Incorrect username or password!");
        }
        
        if (user.getLocked()) {
            throw new BadCredentialsException("User is Locked");
        }
        
        if (Boolean.FALSE.equals(user.getIsActive())) {
            throw new BadCredentialsException("User is Inactive");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Incorrect username or password!");
        }

        // if (user.getRoles().stream()
        //         .anyMatch(x -> x.getRoleName().equalsIgnoreCase("SUPER-ADMIN"))
        //         && Objects.nonNull(user.getPassword())) {

        //     List<GrantedAuthority> roles = user.getRoles().stream()
        //             .map(x -> new SimpleGrantedAuthority(x.getRoleName())).collect(Collectors.toList());

        //     if (!passwordEncoder.matches(password, user.getPassword())) {
        //         throw new BadCredentialsException("Incorrect username or password!");
        //     }

        //     logger.info("authenticated roles {}", roles);
        //     return new UsernamePasswordAuthenticationToken(user, null, roles);
        // }

        List<GrantedAuthority> roles = user.getRoles().stream()
                .map(x -> new SimpleGrantedAuthority(x.getRoleName())).collect(Collectors.toList());

		// user.setFailedAttempt(0);
        // UserDTO userDTO = new UserDTO();
        // UserMapper.INSTANCE.copyEntityToDto(user, userDTO);
		// try {
        //     userService.create(userDTO);
        // } catch (ServerException e) {
        //     e.printStackTrace();
        // }

        logger.info("authenticated roles {}", roles);
        return new UsernamePasswordAuthenticationToken(user, null, roles);

    }

}
