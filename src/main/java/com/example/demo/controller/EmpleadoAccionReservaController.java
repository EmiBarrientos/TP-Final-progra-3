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
    public List<EmpleadoAccionReservaDTO> getAllEmpleados() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoAccionReservaDTO> getEmpleadoById(@PathVariable Long id) {
        Optional<EmpleadoAccionReservaDTO> empleado = service.findById(id);
        return empleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmpleadoAccionReservaDTO createEmpleado(@RequestBody EmpleadoAccionReservaCrearDTO empleado) {
        return service.save(empleado).get();
    }

    @PutMapping("/{id}")
    public EmpleadoAccionReservaDTO updateEmpleado(@PathVariable Long id, @RequestBody EmpleadoAccionReservaCrearDTO empleadoDetails) {
        return service.updateEmpleado(id,empleadoDetails).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
