package com.example.demo.model;

import com.example.demo.model.enums.TipoHabitacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "costos_habitacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostoHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TipoHabitacion tipoHabitacion;

    @Column(nullable = false)
    private Double costo;
}