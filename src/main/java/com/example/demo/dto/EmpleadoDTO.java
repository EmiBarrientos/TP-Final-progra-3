package com.example.demo.dto;

import com.example.demo.model.Usuario;
import com.example.demo.model.enums.EstadoEmpleado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {

    private Long id;
    private Usuario usuario;
    private Integer horasTrabajadas;
    private EstadoEmpleado estado;

}