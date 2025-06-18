package com.example.demo.auth.servicios;


import com.example.demo.auth.dto.AuthResponse;
import com.example.demo.auth.dto.LoginRequest;
import com.example.demo.auth.dto.RegisterRequest;
import com.example.demo.auth.jwt.JwtService;
import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.enums.Rol;
import com.example.demo.auth.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public AuthResponse login(LoginRequest request){
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           request.getUsername(),
                           request.getPassword()));


           UserDetails user = usuarioRepository.findByUsername(request.getUsername())
                   .orElseThrow(() -> new RuntimeException("usuario no encontrado"));

           String token = jwtService.getToken(user);

           return AuthResponse.builder()
                   .token(token)
                   .build();

       } catch (BadCredentialsException e) {
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
       } catch (Exception e) {
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado: " + e.getMessage());
       }
    }
    public AuthResponse register(RegisterRequest request){
        Usuario usuario= Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .dni(request.getDni())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .rol(request.getRol())
                .build();
        System.out.println("Rol recibido: " + request.getRol());

        usuarioRepository.save(usuario);

        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }

}