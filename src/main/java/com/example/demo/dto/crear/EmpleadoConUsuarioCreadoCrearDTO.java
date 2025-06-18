package com.example.demo.dto.crear;

import com.example.demo.model.enums.EstadoEmpleado;
import com.example.demo.model.enums.EstadoPasajero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoConUsuarioCreadoCrearDTO {


//    private Long usuarioId;

    private Long id;
    private Long usuarioId;
    private Integer horasTrabajadas;
    private EstadoEmpleado estado;

}