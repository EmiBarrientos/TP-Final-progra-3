package com.example.demo.config;

import com.example.demo.auth.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    @Order (2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                .securityMatcher("/api/**", "/auth/**")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/api/usuarios/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/empleados/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/reservas/**").hasAnyRole("EMPLEADO", "PASAJERO", "ADMINISTRADOR")
                        .requestMatchers("/api/pasajeros/**").hasAnyRole("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers("/api/facturacion/**").hasAnyRole("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers("/api/empleadoAccionReserva/**").hasAnyRole("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers("/api/costos-habitacion/**").hasAnyRole("EMPLEADO", "PASAJERO", "ADMINISTRADOR")
                        .requestMatchers("/api/Costo_Servicio/**").hasAnyRole("EMPLEADO", "ADMINISTRADOR", "PASAJERO")
                        .requestMatchers("/api/habitaciones/**").hasAnyRole("ADMINISTRADOR", "EMPLEADO", "PASAJERO")
                        .requestMatchers("/api/servicios/**").hasAnyRole("ADMINISTRADOR", "EMPLEADO", "PASAJERO")
                        .requestMatchers("/api/costo-serv-adicionales/**").hasAnyRole("EMPLEADO", "ADMINISTRADOR", "PASAJERO")
                        .requestMatchers("/api/ServAdicional/**").hasAnyRole("EMPLEADO", "ADMINISTRADOR", "PASAJERO")


                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // 🌐 CORS habilitado globalmente
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }
}
