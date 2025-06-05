package com.example.demo.controller;

import com.example.demo.dto.CostoServicioDTO;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.service.Costo_ServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Costo_Servicio")
@RequiredArgsConstructor
public class Costo_ServicioController {
    @Autowired
    private Costo_ServicioService costoServicioService;

    @GetMapping
    public List<CostoServicioDTO> getAllServicios() {
        return costoServicioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostoServicioDTO> getServicioById(@PathVariable Long id) {
        Optional<CostoServicioDTO> servicio = costoServicioService.findById(id);
        return servicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<CostoServicioDTO> getServicioByNombre(@PathVariable String nombre) {
        CostoServicioDTO costoServicio = costoServicioService.findByNombre(nombre).get();
        if (costoServicio != null) {
            return ResponseEntity.ok(costoServicio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public CostoServicioDTO createServicio(@RequestBody CostoServicioDTO costoServicio) {
        return costoServicioService.save(costoServicio).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostoServicioDTO> updateServicio(@PathVariable Long id, @RequestBody CostoServicioDTO costoServicioDetails) {
        Optional<CostoServicioDTO> servicioDTO = costoServicioService.findById(id);
        if (servicioDTO.isPresent()) {
            CostoServicioDTO updatedCostoServicio = servicioDTO.get();
            ReflectionMapper.actualizarCamposNoNulos(costoServicioDetails,updatedCostoServicio);
            return ResponseEntity.ok(costoServicioService.save(updatedCostoServicio).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Long id) {
        costoServicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}