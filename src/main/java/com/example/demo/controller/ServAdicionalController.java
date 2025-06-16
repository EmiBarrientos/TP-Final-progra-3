package com.example.demo.controller;

import com.example.demo.dto.ServAdicionalDTO;
import com.example.demo.service.ServAdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ServAdicional") // Ruta base: /api/servicios
public class ServAdicionalController {

    private final ServAdicionalService service;

    @Autowired
    public ServAdicionalController(ServAdicionalService service) {
        this.service = service;
    }

    /**
     * GET /api/servicios
     * Lista todos los servicios adicionales.
     * Devuelve 200 OK con la lista de DTOs (vacía si no hay registros).
     */
    @GetMapping
    public ResponseEntity<List<ServAdicionalDTO>> listarTodos() {
        List<ServAdicionalDTO> lista = service.findAllDtos();
        return ResponseEntity.ok(lista);
    }

    /**
     * GET /api/servicios/{id}
     * Obtiene un servicio adicional por su ID.
     * Si existe, devuelve 200 OK con el DTO; si no, 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServAdicionalDTO> obtenerPorId(@PathVariable Long id) {
        Optional<ServAdicionalDTO> maybeDto = service.findDtoById(id);
        return maybeDto
                .map(dto -> ResponseEntity.ok(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /api/servicios
     * Crea un nuevo servicio adicional.
     * Espera un JSON con { "nombre": ..., "descripcion": ... } (sin id).
     * Devuelve 201 Created con el DTO resultante (incluyendo el id generado).
     */
    @PostMapping
    public ResponseEntity<ServAdicionalDTO> crear(@RequestBody ServAdicionalDTO dtoRequest) {
        // dtoRequest.getId() debería venir null o no enviarse
        ServAdicionalDTO dtoGuardado = service.saveDto(dtoRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dtoGuardado);
    }

    /**
     * PUT /api/servicios/{id}
     * Actualiza un servicio existente.
     * El ID viene en la ruta y se fuerza en el DTO para evitar inconsistencias.
     * Si no existe, devuelve 404 Not Found.
     * Si existe, devuelve 200 OK con el DTO actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServAdicionalDTO> actualizar(
            @PathVariable Long id,
            @RequestBody ServAdicionalDTO dtoRequest) {

        // Forzamos que el ID del DTO coincida con el de la ruta
        dtoRequest.setId(id);

        // Verificamos si existe el registro
        if (service.findDtoById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Guardamos (JPA hará merge/UPDATE porque id != null)
        ServAdicionalDTO dtoActualizado = service.saveDto(dtoRequest);
        return ResponseEntity.ok(dtoActualizado);
    }

    /**
     * DELETE /api/servicios/{id}
     * Elimina un servicio adicional.
     * Si no existe, devuelve 404 Not Found.
     * Si existe, borra y responde 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.findDtoById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}