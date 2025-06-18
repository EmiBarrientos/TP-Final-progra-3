package com.example.demo.dto.crear;

<<<<<<< HEAD
import com.example.demo.auth.user.Rol;
=======
import com.example.demo.auth.enums.Rol;
>>>>>>> da2898a8d0c8341af32c290337d8291892917938
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
<<<<<<< HEAD
    private Rol rol; // administrador, empleado, pasajero
    //private Direccion direccion;
=======

    // Dirección descompuesta (por si no usás el objeto embebido Direccion directamente)
>>>>>>> da2898a8d0c8341af32c290337d8291892917938
    private String calle;
    private String numero;
    private String ciudad;
    private String provincia;
    private String codigoPostal;
    private String pais;

    private Rol rol; // o podés usar String si preferís pasarlo como texto



}