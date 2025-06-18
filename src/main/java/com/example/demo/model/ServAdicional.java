package com.example.demo.model;

import com.example.demo.model.enums.ServAdicionalEnum;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Servicios_Adicionales")
public class ServAdicional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated (EnumType.STRING)
    @Column (name = "nombre", nullable = false, unique = true)
    private ServAdicionalEnum nombre;
    private String descripcion;


}
