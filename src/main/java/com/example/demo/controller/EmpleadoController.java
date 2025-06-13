package com.example.demo.controller;

import com.example.demo.dto.EmpleadoDTO;
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
    public List<EmpleadoDTO> getAllEmpleados() {
        return empleadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> getEmpleadoById(@PathVariable Long id) {
        Optional<EmpleadoDTO> empleado = empleadoService.findById(id);
        return empleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmpleadoDTO createEmpleado(@RequestBody EmpleadoCrearDTO empleado) {
        return empleadoService.save(empleado).get();
    }

    @PutMapping("/{id}")
    public EmpleadoDTO updateEmpleado(@PathVariable Long id, @RequestBody EmpleadoCrearDTO empleadoDetails) {
        return empleadoService.updateEmpleado(id,empleadoDetails).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        empleadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}