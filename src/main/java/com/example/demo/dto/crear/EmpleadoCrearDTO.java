package com.example.demo.dto.crear;

import com.example.demo.model.enums.EstadoEmpleado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoCrearDTO {

    private Long id;
    private Long usuarioId;
    private Integer horasTrabajadas;
    private EstadoEmpleado estado;

}