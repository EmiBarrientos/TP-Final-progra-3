package com.example.demo.model;

import com.example.demo.model.enums.EstadoEmpleado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "horas_trabajadas")
    private Integer horasTrabajadas;

    @Enumerated(EnumType.STRING)
    private EstadoEmpleado estado;

    // ya no tiene sentido la vinculacion porque se hace por otro lado EmpleadoAccionReserva
//    @OneToMany(mappedBy = "empleado")
//    private List<Reserva> reservas;

}