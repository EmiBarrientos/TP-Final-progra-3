package com.example.demo.model;

import com.example.demo.model.enums.EstadoReserva;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado_accion_reserva")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoAccionReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;
}