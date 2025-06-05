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
        Optional<Credenciales> credenciales = credencialesServiceOJO.findById(id);
        return credenciales.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Credenciales createCredenciales(@RequestBody Credenciales credenciales) {
        return credencialesServiceOJO.save(credenciales);
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
        credencialesServiceOJO.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{nombreUsuario}")
    public ResponseEntity<Credenciales> getByNombreUsuario(@PathVariable String nombreUsuario) {
        Credenciales credenciales = credencialesServiceOJO.findByNombreUsuario(nombreUsuario);
        if (credenciales != null) {
            return ResponseEntity.ok(credenciales);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}