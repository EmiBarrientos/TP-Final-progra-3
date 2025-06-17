package com.example.demo.dto.crear;

import com.example.demo.auth.user.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCrearDTO {

    private Long id;

    private String username;
    private String password;

    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Rol rol; // administrador, empleado, pasajero
    //private Direccion direccion;
    private String calle;
    private String numero;
    private String ciudad;
    private String provincia;
    private String codigoPostal;
    private String pais;

}