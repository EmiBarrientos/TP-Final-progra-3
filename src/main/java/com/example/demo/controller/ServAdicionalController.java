package com.example.demo.controller;

import com.example.demo.dto.ServAdicionalDTO;
import com.example.demo.service.ServAdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ServAdicional")
public class ServAdicionalController {

    private final ServAdicionalService service;

    @Autowired
    public ServAdicionalController(ServAdicionalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ServAdicionalDTO>> listarTodos() {
        try {
            return ResponseEntity.ok(service.findAllDtos());
        } catch (Exception e) {
            throw new RuntimeException("Error al listar servicios adicionales: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServAdicionalDTO> obtenerPorId(@PathVariable Long id) {
        try {
            return service.findDtoById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener servicio adicional con ID " + id + ": " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<ServAdicionalDTO> crear(@RequestBody ServAdicionalDTO dtoRequest) {
        try {
            ServAdicionalDTO dtoGuardado = service.saveDto(dtoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(dtoGuardado);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear servicio adicional: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServAdicionalDTO> actualizar(@PathVariable Long id,
                                                       @RequestBody ServAdicionalDTO dtoRequest) {
        try {
            dtoRequest.setId(id);
            if (service.findDtoById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            ServAdicionalDTO dtoActualizado = service.saveDto(dtoRequest);
            return ResponseEntity.ok(dtoActualizado);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar servicio adicional: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            if (service.findDtoById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar servicio adicional: " + e.getMessage());
        }
    }
}
