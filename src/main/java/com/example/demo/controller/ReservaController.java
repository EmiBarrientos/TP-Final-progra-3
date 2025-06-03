package com.example.demo.controller;

import com.example.demo.dto.crear.ReservaCrearDTO;
import com.example.demo.dto.ReservaDTO;
import com.example.demo.model.enums.EstadoReserva;
import com.example.demo.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<ReservaDTO> getAllReservas() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Long id) {
        Optional<ReservaDTO> reserva = reservaService.findById(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/fechas")
    public List<ReservaDTO> getReservasBetweenDates(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fin) {
        return reservaService.findByFechaInicioBetween(inicio, fin);
    }

    @GetMapping("/pasajero/{pasajeroId}")
    public List<ReservaDTO> getReservasByPasajero(@PathVariable Long pasajeroId) {
        return reservaService.findByPasajeroId(pasajeroId);
    }

    @GetMapping("/habitacion/{habitacionId}")
    public List<ReservaDTO> getReservasByHabitacion(@PathVariable Long habitacionId) {
        return reservaService.findByHabitacionId(habitacionId);
    }

    //@GetMapping("/estado/{estado}") Corregido abajo por Lucho
    //public List<Reserva> getReservasByEstado(@PathVariable String estado) {
    //    return reservaService.findByEstado(estado);
    //}

    @PostMapping
    public Optional<ReservaDTO> createReserva(@RequestBody ReservaCrearDTO reservaCrearDTO) {
        return reservaService.save(reservaCrearDTO);
    }

    @PutMapping("/{id}")
    public ReservaDTO updateReserva(@PathVariable Long id, @RequestBody ReservaCrearDTO reservaDetails) {
        return reservaService.updateReserva(id,reservaDetails).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // corregido por Lucho
    @GetMapping("/estado/{estado}")
    public List<ReservaDTO> getReservasByEstado(@PathVariable EstadoReserva estado) {
        return reservaService.findByEstado(estado);
    }


}