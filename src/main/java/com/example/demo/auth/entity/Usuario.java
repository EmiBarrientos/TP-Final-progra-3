package com.example.demo.auth.entity;

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

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private String telefono;

    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email no puede estar vacío")
    @Column(nullable = false, unique = true)
    private String email;

    @Embedded
    private Direccion direccion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol; // ADMINISTRADOR, EMPLEADO, PASAJERO

    @JsonIgnore
    @OneToOne(mappedBy = "usuario")
    private Empleado empleado;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario")
    private Pasajero pasajero;

    // Métodos obligatorios de UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
*/
