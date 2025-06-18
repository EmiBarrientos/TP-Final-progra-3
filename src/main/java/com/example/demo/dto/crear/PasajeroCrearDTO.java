package com.example.demo.dto.crear;

import com.example.demo.model.enums.EstadoPasajero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasajeroCrearDTO {


//    private Long usuarioId;

    private Long id;

    // todo lo de usuario
    private Long idUsuario;
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
    private String rol; // o podés usar String si preferís pasarlo como texto



//-----------------------------------------
    private EstadoPasajero estado;
}