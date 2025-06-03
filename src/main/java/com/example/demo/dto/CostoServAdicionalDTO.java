package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CostoServAdicionalDTO {
    private Long id;
    private ServicioDTO nombreServicioAdicional;
    private Double precioUnitario;


}
