package com.example.demo.controller;

import com.example.demo.dto.PasajeroDTO;
import com.example.demo.dto.crear.PasajeroConUsuarioCreadoCrearDTO;
import com.example.demo.dto.crear.PasajeroCrearDTO;
import com.example.demo.mapper.PasajeroMapper;
import com.example.demo.mapper.noIdenticos.PasajeroCrearMapper;
import com.example.demo.model.Pasajero;
import com.example.demo.service.PasajeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pasajeros")
@RequiredArgsConstructor
public class PasajeroController {

    private final PasajeroService pasajeroService;
    private final PasajeroCrearMapper pasajeroCrearMapper;
    private final PasajeroMapper pasajeroMapper;

    @GetMapping
    public ResponseEntity<List<Pasajero>> getAllPasajeros() {
        try {
            return ResponseEntity.ok(pasajeroService.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener pasajeros: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PasajeroDTO> getPasajeroById(@PathVariable Long id) {
        try {
            Optional<PasajeroDTO> pasajero = pasajeroService.findById(id);
            return pasajero.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar pasajero por ID: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<PasajeroDTO> createPasajero(@RequestBody PasajeroConUsuarioCreadoCrearDTO pasajero) {
        try {
            Optional<PasajeroDTO> creado = pasajeroService.save(pasajero);
            return creado.map(ResponseEntity::ok)
                    .orElseThrow(() -> new RuntimeException("No se pudo crear el pasajero"));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear pasajero: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PasajeroDTO> updatePasajero(@PathVariable Long id, @RequestBody PasajeroConUsuarioCreadoCrearDTO pasajeroDetails) {
        try {
            Optional<PasajeroDTO> actualizado = pasajeroService.updatePasajero(id, pasajeroDetails);
            return actualizado.map(ResponseEntity::ok)
                    .orElseThrow(() -> new RuntimeException("No se pudo actualizar el pasajero"));
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar pasajero: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePasajero(@PathVariable Long id) {
        try {
            pasajeroService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar pasajero: " + e.getMessage());
        }
    }
}
