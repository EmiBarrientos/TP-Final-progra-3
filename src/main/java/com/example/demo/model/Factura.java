package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "facturas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relación a la reserva para la que se genera esta factura.
     * (Asumimos que cada factura corresponde a una sola reserva.)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id", nullable = false)
    private Reserva reserva;

    /**
     * Fecha en que se emitió la factura.
     */
    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    /**
     * Monto total facturado (suma de todos los ítems).
     */
    @Column(nullable = false)
    private Double total;
}
