package com.example.demo.controller;

import com.example.demo.dto.FacturaDTO;
import com.example.demo.service.util.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;


    @GetMapping("/previsualizar/{reservaId}")
    public ResponseEntity<FacturaDTO> previsualizarFactura(@PathVariable Long reservaId) {
        try {
            FacturaDTO dto = facturaService.generarFacturaParaReserva(reservaId);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            throw new RuntimeException("Error al previsualizar la factura para la reserva " + reservaId + ": " + e.getMessage());
        }
    }


    @PostMapping("/generar/{reservaId}")
    public ResponseEntity<FacturaDTO> generarFactura(@PathVariable Long reservaId) {
        try {
            FacturaDTO dtoGuardado = facturaService.generarYGuardarFactura(reservaId);
            return ResponseEntity.ok(dtoGuardado);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar y guardar la factura para la reserva " + reservaId + ": " + e.getMessage());
        }
    }
}
