package com.example.demo.auth.controller;

import com.example.demo.auth.dto.UsuarioDTO;
import com.example.demo.dto.crear.UsuarioCrearDTO;
import com.example.demo.auth.servicios.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        try {
            return ResponseEntity.ok(usuarioService.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener usuarios: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        try {
            Optional<UsuarioDTO> usuario = usuarioService.findById(id);
            return usuario.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar usuario por ID: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioCrearDTO usuario) {
        try {
            Optional<UsuarioDTO> creado = usuarioService.save(usuario);
            return creado.map(ResponseEntity::ok)
                    .orElseThrow(() -> new RuntimeException("No se pudo crear el usuario"));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear usuario: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioCrearDTO usuarioDetails) {
        try {
            Optional<UsuarioDTO> actualizado = usuarioService.updateUsuario(id, usuarioDetails);
            return actualizado.map(ResponseEntity::ok)
                    .orElseThrow(() -> new RuntimeException("No se pudo actualizar el usuario"));
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar usuario: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar usuario: " + e.getMessage());
        }
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<UsuarioDTO> getUsuarioByDni(@PathVariable String dni) {
        try {
            Optional<UsuarioDTO> usuario = usuarioService.findByDni(dni);
            return usuario.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar usuario por DNI: " + e.getMessage());
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> getUsuarioByEmail(@PathVariable String email) {
        try {
            Optional<UsuarioDTO> usuario = usuarioService.findByEmail(email);
            return usuario.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar usuario por email: " + e.getMessage());
        }
    }

}
