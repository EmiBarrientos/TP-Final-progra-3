package com.example.demo.dto;

import com.example.demo.model.enums.TipoHabitacion;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostoHabitacionDTO {
    private Long id;

    @NotNull(message = "El tipo de habitación no puede ser nulo")
    private TipoHabitacion tipoHabitacion;

    @NotNull(message = "El costo no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El costo debe ser mayor que 0")
    @Digits(integer = 6, fraction = 2, message = "El costo debe tener máximo 6 dígitos enteros y 2 decimales")
    private Double costo;
}