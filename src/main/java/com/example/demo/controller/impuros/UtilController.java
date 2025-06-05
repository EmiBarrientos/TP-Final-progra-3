package com.example.demo.controller.impuros;

import com.example.demo.dto.HabitacionDTO;
import com.example.demo.model.enums.EstadoReserva;
import com.example.demo.service.util.Util_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/util")
@RequiredArgsConstructor
public class UtilController {

    private final Util_Service util_Service;

    @GetMapping("/disponibles") // Devuelve habitaciones que no tienen reserva
    public ResponseEntity<List<HabitacionDTO>> obtenerHabitacionesDisponibles(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        if (fechaInicio == null || fechaFin == null || fechaInicio.isAfter(fechaFin)) {
            return ResponseEntity.badRequest().build();
        }

        List<HabitacionDTO> disponibles = util_Service.obtenerHabitacionesDisponibles(fechaInicio, fechaFin);
        return ResponseEntity.ok(disponibles);
    }


    // Cambia el estado (PENDIENTE, CONFIRMADA, CHECK_IN, EN_CURSO, CHECK_OUT, CANCELADA, NO_VINO)
    // de una reserva y guarda que empleado realizo dicho cambio
    @PostMapping("/{reservaId}/asignar-empleado/{empleadoId}")
    public ResponseEntity<?> asignarEmpleadoAReserva( // Ejemplo de la direc /api/reservas/{reservaId}/asignar-empleado/{empleadoId}?accionEstado=VALOR
            @PathVariable long reservaId,
            @PathVariable long empleadoId,
            @RequestParam EstadoReserva accionEstado) {

        try {
            util_Service.asignarEmpleadoReserva(reservaId, empleadoId, accionEstado);
            return ResponseEntity.ok().build();

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // modificar el pasajero de una reserva
    @PostMapping("/{reservaId}/asignar-pasajero/{pasajeroId}")
    public ResponseEntity<?> asignarPasajeroAReserva( // Ejemplo de la direc /api/reservas/{reservaId}/asignar-empleado/{empleadoId}?accionEstado=VALOR
                                                      @PathVariable long reservaId,
                                                      @PathVariable long pasajeroId){
        try {
            util_Service.asignarPasajeroAReserva(reservaId, pasajeroId);
            return ResponseEntity.ok().build();

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
