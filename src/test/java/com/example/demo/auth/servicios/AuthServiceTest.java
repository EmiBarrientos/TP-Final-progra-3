package com.example.demo.auth.servicios;

import com.example.demo.auth.dto.AuthResponse;
import com.example.demo.auth.dto.LoginRequest;
import com.example.demo.auth.dto.RegisterRequest;
import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.enums.Rol;
import com.example.demo.auth.jwt.JwtService;
import com.example.demo.auth.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class AuthServiceTest {


    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceServicio authServiceServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void register_deberiaRegistrarUsuarioYDevolverToken() {
        // Arrange
        RegisterRequest request = RegisterRequest.builder()
                .username("gabi")
                .password("1234")
                .dni("12345678")
                .nombre("Gabriela")
                .apellido("Tech")
                .email("gabi@example.com")
                .rol(Rol.EMPLEADO)
                .build();

        String passwordCifrada = "hashed1234";
        String tokenEsperado = "jwt-token-falso";

        when(passwordEncoder.encode("1234")).thenReturn(passwordCifrada);
        when(jwtService.getToken(any(Usuario.class))).thenReturn(tokenEsperado);

        // Act
        AuthResponse response = authServiceServicio.register(request);

        // Assert
        assertNotNull(response);
        assertEquals(tokenEsperado, response.getToken());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void login_deberiaAutenticarYDevolverToken() {
        // Arrange
        LoginRequest request = new LoginRequest("gabi", "1234");
        Usuario usuario = Usuario.builder()
                .username("gabi")
                .password("hashed1234")
                .rol(Rol.EMPLEADO)
                .build();

        String tokenEsperado = "token-falso";

        when(usuarioRepository.findByUsername("gabi")).thenReturn(Optional.of(usuario));
        when(jwtService.getToken(usuario)).thenReturn(tokenEsperado);

        // Act
        AuthResponse response = authServiceServicio.login(request);

        // Assert
        assertNotNull(response);
        assertEquals(tokenEsperado, response.getToken());

        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken("gabi", "1234")
        );

        verify(usuarioRepository, times(1)).findByUsername("gabi");
        verify(jwtService, times(1)).getToken(usuario);
    }


}