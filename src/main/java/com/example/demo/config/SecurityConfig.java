package com.example.demo.config;


import com.example.demo.auth.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf->   ///Cross-site -request Forgery
                        csrf.disable())
                .authorizeHttpRequests(
                        authRequest ->
                                authRequest
                                        .requestMatchers("/auth/**").permitAll()
                                        .requestMatchers("/api/usuarios/**").hasAnyRole("ADMINISTRADOR")
                                        .requestMatchers("/api/reservas/**").hasAnyRole("EMPLEADO","ADMINISTRADOR")
                                        .requestMatchers("/api/empleados/**").hasAnyRole("ADMINISTRADOR")
                                        .requestMatchers("/api/pasajeros/**").hasAnyRole("EMPLEADO","ADMINISTRADOR")
                                        .requestMatchers("/api/facturacion/**").hasAnyRole("EMPLEADO","ADMINISTRADOR")
                                        .requestMatchers("/api/empleadoAccionReserva/**").hasAnyRole("EMPLEADO","ADMINISTRADOR")
                                        .requestMatchers("/api/costos-habitacion/**").hasAnyRole("EMPLEADO","PASAJERO","ADMINISTRADOR")
                                        .requestMatchers("/api/Costo_Servicio/**").hasAnyRole("EMPLEADO","ADMINISTRADOR","PASAJERO")
                                        .requestMatchers("/api/habitaciones/**").hasAnyRole("ADMINISTRADOR","EMPLEADO","PASAJERO")
                                        .requestMatchers("/api/servicios/**").hasAnyRole("ADMINISTRADOR","EMPLEADO","PASAJERO")
                                        .requestMatchers("/api/reservas/**").hasAnyRole("EMPLEADO","PASAJERO","ADMINISTRADOR")
                                        .requestMatchers("/api/costo-serv-adicionales/**").hasAnyRole("EMPLEADO","ADMINISTRADOR","PASAJERO")
                                        .requestMatchers("/api/empleadoAccionReserva/**").hasAnyRole("EMPLEADO","ADMINISTRADOR")
                                        .requestMatchers("/api/ServAdicional/**").hasAnyRole("EMPLEADO","ADMINISTRADOR","PASAJERO")

                                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }



   /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // 👉 Permitir todas las rutas
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .build(); // 👈 NO seteás authenticationProvider ni filtros
    }

*/
}