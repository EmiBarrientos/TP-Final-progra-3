package com.example.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SwaggerSecurityConfig {

    @Bean
    @Order(1) // 🔥 Esto le da prioridad sobre el filtro con JWT
    public SecurityFilterChain swaggerSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatchers(matchers -> matchers
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**"
                        )
                )
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .csrf(csrf -> csrf.disable())
                .build();
    }
}

