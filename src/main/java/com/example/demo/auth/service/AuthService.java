package com.example.demo.auth.service;


import com.example.demo.auth.dto.AuthResponse;
import com.example.demo.auth.dto.LoginRequest;
import com.example.demo.auth.dto.RegisterRequest;
import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.jwt.JwtService;
import com.example.demo.auth.repository.UsuarioRepository;
import com.example.demo.auth.user.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request){
        return null;
    }
    public AuthResponse register(RegisterRequest request){
        Usuario usuario= Usuario.builder()
                .username(request.getUsername())
                .password(request.getPassword())
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
