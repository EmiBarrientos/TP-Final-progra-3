package com.example.demo.controller.cosas;

import com.example.demo.dto.CostoServAdicionalDTO;
import com.example.demo.service.VERCostoServAdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/costo-serv-adicionales")
public class CostoServAdicionalController {

    private final VERCostoServAdicionalService costoService;

    @Autowired
    public CostoServAdicionalController(VERCostoServAdicionalService costoService) {
        this.costoService = costoService;
    }

    // 1) LISTAR TODOS → devuelve List<CostoServAdicionalDTO>
    @GetMapping
    public ResponseEntity<List<CostoServAdicionalDTO>> listarTodos() {
        List<CostoServAdicionalDTO> listaDTO = costoService.findAllDTO();
        return ResponseEntity.ok(listaDTO);
    }

    // 2) OBTENER POR ID → devuelve un CostoServAdicionalDTO o 404
    @GetMapping("/{id}")
    public ResponseEntity<CostoServAdicionalDTO> obtenerPorId(@PathVariable Long id) {
        return costoService.findByIdDTO(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3) CREAR NUEVO → recibe CostoServAdicionalDTO (sin id) y devuelve el DTO creado
    @PostMapping
    public ResponseEntity<CostoServAdicionalDTO> crear(@RequestBody CostoServAdicionalDTO dtoRequest) {
        try {
            CostoServAdicionalDTO dtoCreado = costoService.saveFromDTO(dtoRequest);
            return ResponseEntity.ok(dtoCreado);
        } catch (RuntimeException e) {
            // Si el nombre del enum era inválido, devolvemos Bad Request
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 4) ACTUALIZAR EXISTENTE → recibe CostoServAdicionalDTO (con id) y devuelve el DTO actualizado
    @PutMapping("/{id}")
    public ResponseEntity<CostoServAdicionalDTO> actualizar(
            @PathVariable Long id,
            @RequestBody CostoServAdicionalDTO dtoRequest) {

        // Primero verificamos que exista el registro
        return costoService.findByIdDTO(id)
                .map(existenteDTO -> {
                    // Forzamos que el id del DTO coincida con el PathVariable
                    dtoRequest.setId(id);
                    try {
                        CostoServAdicionalDTO dtoActualizado = costoService.saveFromDTO(dtoRequest);
                        return ResponseEntity.ok(dtoActualizado);
                    } catch (RuntimeException e) {
                        return ResponseEntity.badRequest().<CostoServAdicionalDTO>build();
                    }
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5) ELIMINAR POR ID → devuelve 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        costoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
