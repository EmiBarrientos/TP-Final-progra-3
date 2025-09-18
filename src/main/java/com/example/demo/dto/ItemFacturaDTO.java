package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemFacturaDTO {

    private String nombreServicio;  // Ej: "LAVANDERIA"
    private Integer cantidadTotal;  // Ej: 3
    private Double precioUnitario;  // Ej: 50.0
    private Double subTotal;
}
