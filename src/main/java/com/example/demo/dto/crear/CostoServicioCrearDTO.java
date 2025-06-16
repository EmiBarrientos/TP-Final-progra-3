package com.example.demo.dto.crear;

import com.example.demo.model.enums.ServicioEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostoServicioCrearDTO {

    private Long id;
    private ServicioEnum nombre;
    private Double costo;
}
