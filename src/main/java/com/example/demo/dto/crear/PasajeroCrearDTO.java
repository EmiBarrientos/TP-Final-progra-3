package com.example.demo.dto.crear;

import com.example.demo.model.enums.EstadoPasajero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasajeroCrearDTO {
    private Long id;
    private Long usuarioId;
    private EstadoPasajero estado;
}