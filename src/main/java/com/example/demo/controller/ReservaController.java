package com.example.demo.controller;

import com.example.demo.dto.ReservaDTO;
import com.example.demo.dto.crear.ReservaCrearDTO;
import com.example.demo.model.Reserva;
import com.example.demo.model.enums.EstadoReserva;
import com.example.demo.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> getAllReservas() {
        try {
            return ResponseEntity.ok(reservaService.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las reservas: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Long id) {
        try {
            Optional<ReservaDTO> reserva = reservaService.findById(id);
            return reserva.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la reserva por ID: " + e.getMessage());
        }
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<ReservaDTO>> getReservasBetweenDates(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fin) {
        try {
            return ResponseEntity.ok(reservaService.findByFechaInicioBetween(inicio, fin));
        } catch (Exception e) {
            throw new RuntimeException("Error al filtrar reservas por fechas: " + e.getMessage());
        }
    }

    @GetMapping("/pasajero/{pasajeroId}")
    public ResponseEntity<List<ReservaDTO>> getReservasByPasajero(@PathVariable Long pasajeroId) {
        try {
            return ResponseEntity.ok(reservaService.findByPasajeroId(pasajeroId));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar reservas por pasajero: " + e.getMessage());
        }
    }

    @GetMapping("/habitacion/{habitacionId}")
    public ResponseEntity<List<ReservaDTO>> getReservasByHabitacion(@PathVariable Long habitacionId) {
        try {
            return ResponseEntity.ok(reservaService.findByHabitacionId(habitacionId));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar reservas por habitación: " + e.getMessage());
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ReservaDTO>> getReservasByEstado(@PathVariable EstadoReserva estado) {
        try {
            return ResponseEntity.ok(reservaService.findByEstado(estado));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar reservas por estado: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody ReservaCrearDTO reservaCrearDTO) {
        try {
            Optional<Reserva> creada = reservaService.save(reservaCrearDTO);
            return creada.map(ResponseEntity::ok)
                    .orElseThrow(() -> new RuntimeException("No se pudo crear la reserva"));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la reserva: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> updateReserva(@PathVariable Long id, @RequestBody ReservaCrearDTO reservaDetails) {
        try {
            Optional<ReservaDTO> actualizada = reservaService.updateReserva(id, reservaDetails);
            return actualizada.map(ResponseEntity::ok)
                    .orElseThrow(() -> new RuntimeException("No se pudo actualizar la reserva"));
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la reserva: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        try {
            reservaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la reserva: " + e.getMessage());
        }
    }


    @GetMapping("/habitaciones-reservadas")
    public ResponseEntity<List<Long>> getHabitacionesReservadas(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fin) {
        try {
            return ResponseEntity.ok(reservaService.obtenerHabitacionesReservadas(inicio, fin));
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener habitaciones reservadas: " + e.getMessage());
        }
    }



}
