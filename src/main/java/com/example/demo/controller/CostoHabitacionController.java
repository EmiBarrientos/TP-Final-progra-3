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
        try {
            return costoHabitacionService.findAll();
        }catch (Exception e){

            throw new RuntimeException("Error al obtener todos los costos de habitaciones" + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostoHabitacionDTO> getCostoHabitacionById(@PathVariable Long id) {
        try {
            Optional<CostoHabitacionDTO> costoHabitacion = costoHabitacionService.findById(id);
            return costoHabitacion.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar costo de habitación por ID: " + e.getMessage());
        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<CostoHabitacionDTO> getCostoByTipoHabitacion(@PathVariable String tipo) {
        try {
            Optional<CostoHabitacionDTO> costoHabitacion = costoHabitacionService.findByTipoHabitacion(tipo);
            return costoHabitacion.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar costo por tipo de habitación: " + e.getMessage());
        }
    }

    @PostMapping
    public CostoHabitacionDTO createCostoHabitacion(@RequestBody CostoHabitacionDTO costoHabitacion) {
        try {
            return costoHabitacionService.save(costoHabitacion)
                    .orElseThrow(() -> new RuntimeException("No se pudo crear el costo de habitación"));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear costo de habitación: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostoHabitacionDTO> updateCostoHabitacion(@PathVariable Long id, @RequestBody CostoHabitacionDTO costoHabitacionDetails) {
        try {
            Optional<CostoHabitacionDTO> costoHabitacion = costoHabitacionService.findById(id);
            if (costoHabitacion.isPresent()) {
                CostoHabitacionDTO updatedCosto = costoHabitacion.get();
                ReflectionMapper.actualizarCamposNoNulos(costoHabitacionDetails, updatedCosto);
                Optional<CostoHabitacionDTO> guardado = costoHabitacionService.save(updatedCosto);
                return guardado.map(ResponseEntity::ok)
                        .orElseThrow(() -> new RuntimeException("No se pudo guardar la actualización"));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar costo de habitación: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCostoHabitacion(@PathVariable Long id) {
        try {
            costoHabitacionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar costo de habitación: " + e.getMessage());
        }
    }
    }



