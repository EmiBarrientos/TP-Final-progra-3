package com.example.demo.model;

import com.example.demo.auth.entity.Usuario;
import com.example.demo.model.enums.EstadoPasajero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pasajeros")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pasajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private EstadoPasajero estado;

    @OneToMany(mappedBy = "pasajero")
    private List<Reserva> reservas;
}