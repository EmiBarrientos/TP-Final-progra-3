package com.example.demo.auth.controller;

<<<<<<< HEAD

import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.example.demo.auth.dto.UsuarioDTO;
import com.example.demo.dto.crear.UsuarioCrearDTO;
import com.example.demo.auth.servicios.UsuarioService;
import lombok.RequiredArgsConstructor;
>>>>>>> da2898a8d0c8341af32c290337d8291892917938
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
<<<<<<< HEAD
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        if (usuario.isPresent()) {
            Usuario updatedUsuario = usuario.get();
            // Actualizar campos aquí
            return ResponseEntity.ok(usuarioService.save(updatedUsuario));
        } else {
            return ResponseEntity.notFound().build();
=======

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
>>>>>>> da2898a8d0c8341af32c290337d8291892917938
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
<<<<<<< HEAD
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /*

       estos tres lo mismo
       @PostMapping
    public UsuarioDTO createUsuario(@RequestBody UsuarioCrearDTO usuario) {
        return usuarioService.save(usuario).get();
    }

    @PutMapping("/{id}")
    public UsuarioDTO updateUsuario(@PathVariable Long id, @RequestBody UsuarioCrearDTO usuarioDetails) {
        return usuarioService.updateUsuario(id,usuarioDetails).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}*/



}
=======
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar usuario: " + e.getMessage());
        }
    }
}
>>>>>>> da2898a8d0c8341af32c290337d8291892917938
