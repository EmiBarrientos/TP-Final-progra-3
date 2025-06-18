package com.example.demo.auth.entity;

<<<<<<< HEAD
import com.example.demo.auth.user.Rol;
import com.example.demo.model.Credenciales;
import com.example.demo.model.Empleado;
import com.example.demo.model.Pasajero;
import com.example.demo.model.embeddable.Direccion;
import jakarta.persistence.*;
import lombok.*;
=======
import com.example.demo.model.Empleado;
import com.example.demo.model.Pasajero;
import com.example.demo.model.embeddable.Direccion;
import com.example.demo.auth.enums.Rol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
>>>>>>> da2898a8d0c8341af32c290337d8291892917938
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    private String password;
    @Column(nullable = false, unique = true)
    private String dni;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    private String telefono;
<<<<<<< HEAD
=======


    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email no puede estar vacío")
>>>>>>> da2898a8d0c8341af32c290337d8291892917938
    @Column(nullable = false, unique = true)
    private String email;

    @Embedded
    private Direccion direccion;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rol rol; // administrador, empleado, pasajero

<<<<<<< HEAD
=======



    @JsonIgnore
    @OneToOne(mappedBy = "usuario")
    private Empleado empleado;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario")
    private Pasajero pasajero;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }



}



/* viejo usuario
import com.example.demo.model.embeddable.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private String telefono;
    private String email;

    @Embedded
    private Direccion direccion;

    @Column(nullable = false)
    private String permisos; // administrador, empleado, pasajero

>>>>>>> da2898a8d0c8341af32c290337d8291892917938
    @Column(name = "cuenta_no_expirada")
    private boolean cuentaNoExpirada = true;

    @Column(name = "cuenta_no_bloqueada")
    private boolean cuentaNoBloqueada = true;

    @Column(name = "credenciales_no_expiradas")
    private boolean credencialesNoExpiradas = true;

    private boolean activo = true;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Credenciales credenciales;

    @OneToOne(mappedBy = "usuario")
    private Empleado empleado;

    @OneToOne(mappedBy = "usuario")
    private Pasajero pasajero;
<<<<<<< HEAD

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
=======
*/
>>>>>>> da2898a8d0c8341af32c290337d8291892917938
