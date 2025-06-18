package com.example.demo.controller;

import com.example.demo.dto.CostoServicioDTO;
import com.example.demo.dto.crear.CostoServicioCrearDTO;
import com.example.demo.mapper.noIdenticos.CostoServicioCrearMapper;
import com.example.demo.repository.Costo_ServicioRepository;
import com.example.demo.service.Costo_ServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/Costo_Servicio")
@RequiredArgsConstructor
public class Costo_ServicioController {

    @Autowired
    private Costo_ServicioService costoServicioService;

    @Autowired
    private Costo_ServicioRepository costo_ServicioRepository;

    @Autowired
    private CostoServicioCrearMapper costoServicioCrearMapper;

    @GetMapping
    public List<CostoServicioDTO> getAllServicios() {
        return costoServicioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostoServicioDTO> getServicioById(@PathVariable Long id) {
        return ResponseEntity.ok(
                costoServicioService.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Servicio con ID " + id + " no encontrado"))
        );
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<CostoServicioDTO> getServicioByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(
                costoServicioService.findByNombre(nombre)
                        .orElseThrow(() -> new NoSuchElementException("Servicio con nombre '" + nombre + "' no encontrado"))
        );
    }

    @PostMapping
    public ResponseEntity<CostoServicioDTO> createServicio(@RequestBody CostoServicioCrearDTO costoServicio) {
        return ResponseEntity.ok(
                costoServicioService.save(costoServicio)
                        .orElseThrow(() -> new RuntimeException("No se pudo crear el servicio"))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostoServicioDTO> updateServicio(@PathVariable Long id, @RequestBody CostoServicioCrearDTO costoServicioDetails) {
        return ResponseEntity.ok(
                costoServicioService.update(id, costoServicioDetails)
                        .orElseThrow(() -> new NoSuchElementException("No se encontró el servicio para actualizar con ID " + id))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Long id) {
        costoServicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
