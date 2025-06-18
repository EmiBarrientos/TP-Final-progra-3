package com.example.demo.controller.cosas;

import com.example.demo.dto.CostoServAdicionalDTO;
import com.example.demo.service.CostoServAdicionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/costo-serv-adicionales")
@RequiredArgsConstructor
public class CostoServAdicionalController {

    private final CostoServAdicionalService costoService;


    @GetMapping
    public ResponseEntity<List<CostoServAdicionalDTO>> listarTodos() {
        return ResponseEntity.ok(costoService.findAllDTO());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CostoServAdicionalDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                costoService.findByIdDTO(id)
                        .orElseThrow(() -> new NoSuchElementException("Costo adicional con ID " + id + " no encontrado"))
        );
    }


    @PostMapping
    public ResponseEntity<CostoServAdicionalDTO> crear(@RequestBody CostoServAdicionalDTO dtoRequest) {
        return ResponseEntity.ok(
                costoService.saveFromDTO(dtoRequest)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<CostoServAdicionalDTO> actualizar(
            @PathVariable Long id,
            @RequestBody CostoServAdicionalDTO dtoRequest) {

        if (costoService.findByIdDTO(id).isEmpty()) {
            throw new NoSuchElementException("Costo adicional con ID " + id + " no encontrado");
        }

        dtoRequest.setId(id);
        return ResponseEntity.ok(
                costoService.saveFromDTO(dtoRequest)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (costoService.findByIdDTO(id).isEmpty()) {
            throw new NoSuchElementException("Costo adicional con ID " + id + " no encontrado");
        }

        costoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
