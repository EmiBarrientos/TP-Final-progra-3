package com.example.demo.auth.dto;

import com.example.demo.model.embeddable.Direccion;
import com.example.demo.auth.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Direccion direccion;
    private Rol rol; // administrador, empleado, pasajero


}