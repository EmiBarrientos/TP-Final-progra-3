package com.example.demo.service.util;

import com.example.demo.dto.FacturaDTO;
import com.example.demo.dto.ItemFacturaDTO;
import com.example.demo.model.CostoServAdicional;
import com.example.demo.model.Factura;
import com.example.demo.model.Reserva;
import com.example.demo.repository.FacturaRepository;
import com.example.demo.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FacturaService {

    @Autowired private ReservaRepository reservaRepo;
    @Autowired private FacturaRepository facturaRepo;
    /**
     * Genera un FacturaDTO en memoria (no persiste en BD).
     * Recorre directamente el Map<CostoServAdicional,Integer>.
     */
    public FacturaDTO generarFacturaParaReserva(Long reservaId) {
        Reserva reserva = reservaRepo.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada: " + reservaId));

        // 1) Tomamos el Map que relaciona CostoServAdicional → cantidad
        Map<CostoServAdicional, Integer> mapa = reserva.getServiciosAdicionalesConCantidad();

        List<ItemFacturaDTO> items = new ArrayList<>();
        double total = 0.0;

        // 2) Recorremos cada par (CostoServAdicional, cantidad)
        for (Map.Entry<CostoServAdicional, Integer> entry : mapa.entrySet()) {
            CostoServAdicional costo = entry.getKey();   // la entidad completa
            Integer cantidad = entry.getValue();        // cuántas unidades pidió el huésped

            // 2.1) Obtenemos el nombre del servicio (de la entidad relacionada)
            String nombreServicio = costo.getNombre()
                                   .name();

            // 2.2) Tomamos el precio unitario directamente
            Double precioUnitario = costo.getPrecio();

            // 2.3) Calculamos el subtotal
            Double subTotal = precioUnitario * cantidad;
            total += subTotal;

            // 2.4) Armar el ItemFacturaDTO
            ItemFacturaDTO item = new ItemFacturaDTO(
                    nombreServicio,
                    cantidad,
                    precioUnitario,
                    subTotal
            );
            items.add(item);
        }

        // 3) Construimos el FacturaDTO con toda la info
        FacturaDTO dto = new FacturaDTO();
        dto.setFacturaId(null); // solo vista, luego si guardas queda el ID real
        dto.setReservaId(reservaId);
        dto.setNombrePasajero(
                reserva.getPasajero().getUsuario().getNombre() + " " +
                        reserva.getPasajero().getUsuario().getApellido()
        );
        dto.setFechaEmision(LocalDate.now());
        dto.setItems(items);
        dto.setTotal(total);

        return dto;
    }

    /**
     * Genera la factura y la guarda en BD. Devuelve el DTO con facturaId asignado.
     */
    @Transactional
    public FacturaDTO generarYGuardarFactura(Long reservaId) {
        // 1) Genera el DTO en memoria
        FacturaDTO dto = generarFacturaParaReserva(reservaId);

        // 2) Persiste la entidad Factura
        Factura facturaEntity = new Factura();
        facturaEntity.setReserva(
                reservaRepo.findById(reservaId)
                        .orElseThrow(() -> new RuntimeException("Reserva no encontrada: " + reservaId))
        );
        facturaEntity.setFechaEmision(dto.getFechaEmision());
        facturaEntity.setTotal(dto.getTotal());

        facturaEntity = facturaRepo.save(facturaEntity);

        // 3) Asigna el ID generado al DTO y lo retorna
        dto.setFacturaId(facturaEntity.getId());
        return dto;
    }

}