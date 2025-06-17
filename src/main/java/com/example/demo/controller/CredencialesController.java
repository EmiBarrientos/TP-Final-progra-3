package com.example.demo.controller;

import com.example.demo.model.Credenciales;
import com.example.demo.service.CredencialesServiceOJO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/credenciales")
@RequiredArgsConstructor
public class CredencialesController {
    @Autowired
    private CredencialesServiceOJO credencialesServiceOJO;

    @GetMapping("/{id}")
    public ResponseEntity<Credenciales> getCredencialesById(@PathVariable Long id) {
        try {
            Optional<Credenciales> credenciales = credencialesServiceOJO.findById(id);
            return credenciales.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar credenciales por ID: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Credenciales> createCredenciales(@RequestBody Credenciales credenciales) {
        try {
            Credenciales creada = credencialesServiceOJO.save(credenciales);
            return ResponseEntity.ok(creada);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear credenciales: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Credenciales> updateCredenciales(@PathVariable Long id, @RequestBody Credenciales credencialesDetails) {
        Optional<Credenciales> credenciales = credencialesServiceOJO.findById(id);
        if (credenciales.isPresent()) {
            Credenciales updatedCredenciales = credenciales.get();
            // Actualizar campos aquí
            return ResponseEntity.ok(credencialesServiceOJO.save(updatedCredenciales));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredenciales(@PathVariable Long id) {
        try {
            credencialesServiceOJO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar credenciales: " + e.getMessage());
        }
    }

    @GetMapping("/usuario/{nombreUsuario}")
    public ResponseEntity<Credenciales> getByNombreUsuario(@PathVariable String nombreUsuario) {
        try {
            Credenciales credenciales = credencialesServiceOJO.findByNombreUsuario(nombreUsuario);
            if (credenciales != null) {
                return ResponseEntity.ok(credenciales);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar credenciales por nombre de usuario: " + e.getMessage());
        }
    }
}