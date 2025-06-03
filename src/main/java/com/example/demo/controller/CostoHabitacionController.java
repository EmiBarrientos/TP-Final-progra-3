package com.example.demo.controller;

import com.example.demo.dto.CostoHabitacionDTO;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.service.CostoHabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/costos-habitacion")
@RequiredArgsConstructor
public class CostoHabitacionController {
    @Autowired
    private CostoHabitacionService costoHabitacionService;

    @GetMapping
    public List<CostoHabitacionDTO> getAllCostosHabitacion() {
        return costoHabitacionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostoHabitacionDTO> getCostoHabitacionById(@PathVariable Long id) {
        Optional<CostoHabitacionDTO> costoHabitacion = costoHabitacionService.findById(id);
        return costoHabitacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<CostoHabitacionDTO> getCostoByTipoHabitacion(@PathVariable String tipo) {
        CostoHabitacionDTO costoHabitacion = costoHabitacionService.findByTipoHabitacion(tipo).get();
        if (costoHabitacion != null) {
            return ResponseEntity.ok(costoHabitacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public CostoHabitacionDTO createCostoHabitacion(@RequestBody CostoHabitacionDTO costoHabitacion) {
        return costoHabitacionService.save(costoHabitacion).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostoHabitacionDTO> updateCostoHabitacion(@PathVariable Long id, @RequestBody CostoHabitacionDTO costoHabitacionDetails) {
        Optional<CostoHabitacionDTO> costoHabitacion = costoHabitacionService.findById(id);
        if (costoHabitacion.isPresent()) {
            CostoHabitacionDTO updatedCostoHabitacion = costoHabitacion.get();
            ReflectionMapper.actualizarCamposNoNulos(costoHabitacionDetails,updatedCostoHabitacion);
            return ResponseEntity.ok(costoHabitacionService.save(updatedCostoHabitacion).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCostoHabitacion(@PathVariable Long id) {
        costoHabitacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}