package com.example.demo.dto;

import com.example.demo.model.enums.ServicioEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostoServicioDTO {
    private Long id;

    @NotNull(message = "El nombre del servicio no puede ser nulo")
    private ServicioEnum nombre;

    @DecimalMin(value = "0.0", message = "El costo no puede ser negativo")
    @Digits(integer = 6, fraction = 2, message = "El costo debe tener máximo 6 dígitos enteros y 2 decimales")
    private Double costo;

}
