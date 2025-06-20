package com.example.demo.auth.service;


import com.example.demo.auth.dto.AuthResponse;
import com.example.demo.auth.dto.LoginRequest;
import com.example.demo.auth.dto.RegisterRequest;
import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.enums.Rol;
import com.example.demo.auth.jwt.JwtService;
import com.example.demo.auth.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service ("service")
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));



        UserDetails user=userDetailsService.loadUserByUsername(request.getUsername());




        String token= jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }



    public AuthResponse register(RegisterRequest request){
        Usuario usuario= Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .dni(request.getDni())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .rol(Rol.USUARIO)
                .build();

        usuarioRepository.save(usuario);

        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }

}