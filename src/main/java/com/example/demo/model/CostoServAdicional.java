package com.example.demo.model;

import com.example.demo.model.enums.ServAdicionalEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "servicios adicionales")
public class CostoServAdicional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ServAdicionalEnum nombre;

    @Column(nullable = false)
    private Double precio;

}