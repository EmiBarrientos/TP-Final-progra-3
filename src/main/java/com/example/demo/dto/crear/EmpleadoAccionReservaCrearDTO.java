package com.example.demo.dto.crear;

import com.example.demo.model.enums.EstadoReserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoAccionReservaCrearDTO {

    private Long id;
    private EstadoReserva estado;
    private Long idEmpleado;
    private Long idReserva;

}