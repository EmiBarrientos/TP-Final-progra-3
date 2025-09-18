package com.example.demo.dto.crear;

import com.example.demo.auth.enums.Rol;
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

    // Dirección descompuesta (por si no usás el objeto embebido Direccion directamente)
    private String calle;
    private String numero;
    private String ciudad;
    private String provincia;
    private String codigoPostal;
    private String pais;

    private Rol rol; // o podés usar String si preferís pasarlo como texto



}