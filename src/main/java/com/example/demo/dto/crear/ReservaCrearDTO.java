package com.example.demo.dto.crear;

import com.example.demo.model.enums.EstadoReserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaCrearDTO {
    private Long id;
    private Long pasajeroId;
    private Long habitacionId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoReserva estado;
    private String observaciones;
    // private Map<EstadoReserva,EmpleadoAccionReserva> estadoReservaEmpleadoAccionReservaMap;
    // Se hace con el controller de createEmpleadoAccionReservaMap
        // (nuevoEstadoReserva, idReserva, idEmpleado)
        // private EstadoReserva estadoReserva;
        //private EmpleadoAccionReserva empleadoAccionReserva
}