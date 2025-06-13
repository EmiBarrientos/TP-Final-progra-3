package com.example.demo.model;

import com.example.demo.model.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pasajero_id", nullable = false)
    private Pasajero pasajero;


    @ManyToOne
    @JoinColumn(name = "habitacion_id", nullable = false)
    private Habitacion habitacion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    //@ManyToOne
    //@JoinColumn(name = "empleado_id")
    //private Empleado empleado;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "estado")
    private Map<EstadoReserva, EmpleadoAccionReserva> empleadoAccionEstado;

    private String observaciones;

    @ElementCollection
    @CollectionTable(
            name = "reserva_serv_adicional_cantidad",
            joinColumns = @JoinColumn(name = "reserva_id")
    )
    @MapKeyJoinColumn(name = "costo_serv_adicional_id")
    @Column(name = "cantidad")
    private Map<CostoServAdicional, Integer> serviciosAdicionalesConCantidad = new HashMap<>();

}