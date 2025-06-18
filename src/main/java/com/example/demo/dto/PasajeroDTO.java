package com.example.demo.dto;

import com.example.demo.model.Reserva;
import com.example.demo.auth.entity.Usuario;
import com.example.demo.model.enums.EstadoPasajero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasajeroDTO {

    private Long id;
    private Usuario usuario;
    private EstadoPasajero estado;
    private List<Reserva> reservas;

}

