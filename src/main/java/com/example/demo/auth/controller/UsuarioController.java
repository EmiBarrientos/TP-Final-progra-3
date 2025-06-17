package com.example.demo.auth.controller;


import com.example.demo.auth.dto.UsuarioDTO;
import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.service.UsuarioService;
import com.example.demo.dto.crear.UsuarioCrearDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }


    //Listar todos como DTO
    @GetMapping("/dto")
    public ResponseEntity<List<UsuarioDTO>> getAllUsuariosDTO() {
        return ResponseEntity.ok(usuarioService.findAllDTO());
    }



    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Buscar por ID - DTO
    @GetMapping("/dto/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioByIdDTO(@PathVariable Long id) {
        return usuarioService.findByIdDto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*

       mismo metodo de arriba pero esta usando usuario dto, cambiar el medodo de arriba por este
       @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        Optional<UsuarioDTO> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }*/
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    // Crear usuario desde DTO
    @PostMapping("/dto")
    public ResponseEntity<UsuarioDTO> createUsuarioDTO(@RequestBody UsuarioCrearDTO dto) {
        return ResponseEntity.ok(usuarioService.saveDTO(dto).get());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        if (usuario.isPresent()) {
            Usuario updatedUsuario = usuario.get();
            // Actualizar campos aquí
            return ResponseEntity.ok(usuarioService.save(updatedUsuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar usuario desde DTO
    @PutMapping("/dto/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuarioDTO(
            @PathVariable Long id,
            @RequestBody UsuarioCrearDTO dto
    ) {
        return usuarioService.updateUsuarioDTO(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    //  Buscar por DNI
    @GetMapping("/buscar/dni")
    public ResponseEntity<UsuarioDTO> findByDni(@RequestParam String dni) {
        return usuarioService.findByDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  Buscar por Email
    @GetMapping("/buscar/email")
    public ResponseEntity<UsuarioDTO> findByEmail(@RequestParam String email) {
        return usuarioService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

/*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}*/



}