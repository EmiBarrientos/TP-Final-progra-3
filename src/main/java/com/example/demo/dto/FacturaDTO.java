package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDTO {
    private Long facturaId;           // id de la factura (si la guardaste en BD)
    private Long reservaId;           // id de la reserva asociada
    private String nombrePasajero;    // p.ej. “Juan Pérez”
    private LocalDate fechaEmision;   // fecha en que se emite la factura
    private List<ItemFacturaDTO> items;
    private Double total;
}
