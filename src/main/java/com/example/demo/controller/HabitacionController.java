package com.example.demo.controller;

import com.example.demo.dto.HabitacionDTO;
import com.example.demo.dto.crear.HabitacionCrearDTO;
import com.example.demo.mapper.HabitacionMapper;
import com.example.demo.service.HabitacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;


    private final HabitacionMapper habitacionMapper;

    @GetMapping
    public ResponseEntity<List<HabitacionDTO>> getAllHabitaciones() {
        try {
            return ResponseEntity.ok(habitacionService.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las habitaciones: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDTO> getHabitacionById(@PathVariable Long id) {
        try {
            Optional<HabitacionDTO> habitacion = habitacionService.findById(id);
            return habitacion.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar habitación por ID: " + e.getMessage());
        }
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<HabitacionDTO> getHabitacionByNumero(@PathVariable String numero) {
        try {
            Optional<HabitacionDTO> habitacion = habitacionService.findByNumeroHabitacion(numero);
            return habitacion.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar habitación por número: " + e.getMessage());
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<HabitacionDTO>> getHabitacionesByEstado(@PathVariable String estado) {
        try {
            return ResponseEntity.ok(habitacionService.findByEstado(estado));
        } catch (Exception e) {
            throw new RuntimeException("Error al filtrar habitaciones por estado: " + e.getMessage());
        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<HabitacionDTO>> getHabitacionesByTipo(@PathVariable String tipo) {
        try {
            return ResponseEntity.ok(habitacionService.findByTipoHabitacion(tipo));
        } catch (Exception e) {
            throw new RuntimeException("Error al filtrar habitaciones por tipo: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createHabitacion(@Valid @RequestBody HabitacionCrearDTO habitacionDTO,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errores);
        }

        try {
            Optional<HabitacionDTO> creada = habitacionService.save(habitacionDTO);
            return creada.map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                    .orElseThrow(() -> new RuntimeException("No se pudo crear la habitación"));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear habitación: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHabitacion(@PathVariable Long id,
                                              @Valid @RequestBody HabitacionCrearDTO habitacionDetailsDTO,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errores);
        }

        try {
            Optional<HabitacionDTO> actualizada = habitacionService.updateHabitacion(id, habitacionDetailsDTO);
            return actualizada.map(ResponseEntity::ok)
                    .orElseThrow(() -> new RuntimeException("No se pudo actualizar la habitación"));
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar habitación: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable Long id) {
        try {
            habitacionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar habitación: " + e.getMessage());
        }
    }
}



/*
    @GetMapping("/{id}/costo")
    public ResponseEntity<?> calcularCostoHabitacion(@PathVariable Long id) {
        try {
            // 1. Obtener la habitación por ID
            Habitacion habitacion = habitacionService.findById(id).get();
            // 2. Calcular el costo totla de la habitacion usando el servicio
            // el Servicio habitacionService.calcualrCostoTotal hace lo siguiente:
            // recibe una entidad Habitacion (saca su id y su tipo)
                // CostoBase = con el tipo en la tabla costoHabitacion saca cuanto sale la habitacion base
                // Listado de Servicios de la habitacion = con la id en la tabla habitacion_servicios ve cuales son los servicios que tiene activos esa habitacion y genera una List
                // CostoDeLosServicios =con la List de servicios que se genero tiene esa habitacion en la tabla servicios suma el costo de cada servicio
                // Costo total = CostoBase + CostoDeLosServicios

            Double costoTotal = habitacionService.calcularCostoTotal(habitacion);
            // 3. Devolver la respuesta con el costo
            return ResponseEntity.ok(costoTotal);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al calcular el costo de la habitación");
        }
    }
 */

