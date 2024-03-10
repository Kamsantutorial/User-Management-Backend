package com.backend.internal.usermanagement.common.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author Bunthouern CHUOB
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

        @Bean
        public AuthenticationEntryPoint authenticationEntryPoint() {
                return new CustomAuthenticationEntryPoint();
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeRequests(requests -> requests
                                                .antMatchers("/oauth/token", "/oauth/check_token", "/oauth/token_key",
                                                                "/swagger-ui/**", "/oauth/**",
                                                                "/v3/api-docs/**", "/setting/user/all-users",
                                                                "/api/khqrs/images/{transactionDate}/{filename:.+}",
                                                                "/report/**")
                                                .permitAll()
                                                .antMatchers("/**").authenticated())
                                .exceptionHandling(handling -> handling
                                                .authenticationEntryPoint(authenticationEntryPoint()));
        }
}
