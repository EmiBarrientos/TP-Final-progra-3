package com.example.demo.dto;

import com.example.demo.model.EmpleadoAccionReserva;
import com.example.demo.model.Habitacion;
import com.example.demo.model.Pasajero;
import com.example.demo.model.Reserva;
import com.example.demo.model.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private Long id;
    private Pasajero pasajero;
    private Habitacion habitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoReserva estado;
    private String observaciones;
    private Map<EstadoReserva,EmpleadoAccionReserva> estadoReservaEmpleadoAccionReservaMap;
}