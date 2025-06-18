package com.example.demo.controller;

import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.dto.crear.EmpleadoConUsuarioCreadoCrearDTO;
import com.example.demo.dto.crear.EmpleadoCrearDTO;
import com.example.demo.mapper.noIdenticos.EmpleadoCrearMapper;
import com.example.demo.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EmpleadoCrearMapper empleadoCrearMapper;

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> getAllEmpleados() {
        try {
            return ResponseEntity.ok(empleadoService.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener lista de empleados: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> getEmpleadoById(@PathVariable Long id) {
        try {
            Optional<EmpleadoDTO> empleado = empleadoService.findById(id);
            return empleado.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar empleado por ID: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> createEmpleado(@RequestBody EmpleadoConUsuarioCreadoCrearDTO empleado) {
        try {
            Optional<EmpleadoDTO> creado = empleadoService.save(empleado);
            return creado.map(ResponseEntity::ok)
                    .orElseThrow(() -> new RuntimeException("No se pudo crear el empleado"));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear empleado: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> updateEmpleado(@PathVariable Long id, @RequestBody EmpleadoConUsuarioCreadoCrearDTO empleadoDetails) {
        try {
            Optional<EmpleadoDTO> actualizado = empleadoService.updateEmpledo(id, empleadoDetails);
            return actualizado.map(ResponseEntity::ok)
                    .orElseThrow(() -> new RuntimeException("No se pudo actualizar el empleado"));
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar empleado: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        try {
            empleadoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar empleado: " + e.getMessage());
        }
    }
}