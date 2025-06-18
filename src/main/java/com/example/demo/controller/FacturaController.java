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

    /**
     * Previsualizar factura (solo en memoria, no guarda).
     * GET /api/facturas/previsualizar/{reservaId}
     */
    @GetMapping("/previsualizar/{reservaId}")
    public ResponseEntity<FacturaDTO> previsualizarFactura(@PathVariable Long reservaId) {
        try {
            FacturaDTO dto = facturaService.generarFacturaParaReserva(reservaId);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Generar y guardar la factura en BD. Devuelve FacturaDTO con ID asignado.
     * POST /api/facturas/generar/{reservaId}
     */
    @PostMapping("/generar/{reservaId}")
    public ResponseEntity<FacturaDTO> generarFactura(@PathVariable Long reservaId) {
        try {
            FacturaDTO dtoGuardado = facturaService.generarYGuardarFactura(reservaId);
            return ResponseEntity.ok(dtoGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}