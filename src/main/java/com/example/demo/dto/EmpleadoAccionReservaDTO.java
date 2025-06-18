package com.example.demo.dto;

import com.example.demo.model.Empleado;
import com.example.demo.model.Reserva;
import com.example.demo.model.enums.EstadoReserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoAccionReservaDTO {

    private Long id;
    private EstadoReserva estado;
    private Empleado empleado;
    private Reserva reserva;

}