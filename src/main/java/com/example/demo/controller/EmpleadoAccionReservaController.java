package com.example.demo.controller;

import com.example.demo.dto.EmpleadoAccionReservaDTO;
import com.example.demo.dto.crear.EmpleadoAccionReservaCrearDTO;
import com.example.demo.service.EmpleadoAccionReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleadoAccionReserva")
@RequiredArgsConstructor
public class EmpleadoAccionReservaController {
    @Autowired
    private EmpleadoAccionReservaService service;



    @GetMapping
    public ResponseEntity <List<EmpleadoAccionReservaDTO>> getAllEmpleados() {

        try {
            return ResponseEntity.ok(service.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de empleados: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoAccionReservaDTO> getEmpleadoById(@PathVariable Long id) {
        try {
            Optional<EmpleadoAccionReservaDTO> empleado = service.findById(id);
            return empleado.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar empleado por ID: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity <EmpleadoAccionReservaDTO> createEmpleado(@RequestBody EmpleadoAccionReservaCrearDTO empleado) {
        try {
            Optional<EmpleadoAccionReservaDTO> creado = service.save(empleado);
            return creado.map(ResponseEntity::ok)
                    .orElseThrow(() -> new RuntimeException("No se pudo crear el empleado"));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear empleado: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public  ResponseEntity <EmpleadoAccionReservaDTO> updateEmpleado(@PathVariable Long id, @RequestBody EmpleadoAccionReservaCrearDTO empleadoDetails) {
        try {
            Optional<EmpleadoAccionReservaDTO> actualizado = service.updateEmpleado(id, empleadoDetails);
            return actualizado.map(ResponseEntity::ok)
                    .orElseThrow(() -> new RuntimeException("No se pudo actualizar el empleado"));
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar empleado: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar empleado: " + e.getMessage());
        }
    }

}
