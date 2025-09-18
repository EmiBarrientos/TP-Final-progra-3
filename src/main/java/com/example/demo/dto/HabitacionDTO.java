package com.example.demo.dto;

import com.example.demo.model.Reserva;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionDTO {

    private Long id;

    @NotNull(message = "El numero de habitación no puede ser nulo")
    private String numeroHabitacion;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer capacidad;

    @NotNull(message = "El tipo de habitación no puede ser nulo")
    private String tipoHabitacion;

    @NotNull(message = "El estado de habitación no puede ser nulo")
    private String estado;

    private Map<String, Boolean> servicios;

    private List<Reserva> reservas;

}
