package com.example.demo.model;

import com.example.demo.model.enums.EstadoHabitacion;
import com.example.demo.model.enums.ServicioEnum;
import com.example.demo.model.enums.TipoHabitacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "habitaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_habitacion", unique = true, nullable = false)
    private String numeroHabitacion;

    private Integer capacidad;

    @Enumerated(EnumType.STRING)
    private TipoHabitacion tipoHabitacion;

    @Enumerated(EnumType.STRING)
    private EstadoHabitacion estado;

    @ElementCollection
    @CollectionTable(name = "habitacion_servicios", joinColumns = @JoinColumn(name = "habitacion_id"))
    @MapKeyColumn(name = "servicio")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "disponible")
    private Map<ServicioEnum, Boolean> servicios = new EnumMap<>(ServicioEnum.class);

    @OneToMany(mappedBy = "habitacion")
    private List<Reserva> reservas;






}