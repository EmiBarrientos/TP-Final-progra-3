package com.example.demo.service.util;

import com.example.demo.auth.entity.Usuario;
import com.example.demo.dto.FacturaDTO;
import com.example.demo.dto.ItemFacturaDTO;
import com.example.demo.model.CostoServAdicional;
import com.example.demo.model.Pasajero;
import com.example.demo.model.Reserva;
import com.example.demo.model.enums.ServAdicionalEnum;
import com.example.demo.repository.FacturaRepository;
import com.example.demo.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacturaServiceTest {

    @Mock
    private ReservaRepository reservaRepo;

    @Mock
    private FacturaRepository facturaRepo;

    @InjectMocks
    private FacturaService facturaService;

    public FacturaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGenerateFacturaForValidReserva() {
        // Arrange
        Long reservaId = 1L;

        CostoServAdicional servicio = new CostoServAdicional();
        servicio.setNombre(ServAdicionalEnum.SPA);
        servicio.setPrecio(50.0);

        // Crear usuario y pasajero
        Usuario usuario = new Usuario();
        usuario.setNombre("John");
        usuario.setApellido("Doe");

        Pasajero pasajero = new Pasajero();
        pasajero.setUsuario(usuario);

        // Crear reserva
        Reserva reserva = new Reserva();
        reserva.setId(reservaId);
        reserva.setPasajero(pasajero);

        Map<CostoServAdicional, Integer> servicios = new HashMap<>();
        servicios.put(servicio, 2);
        reserva.setServiciosAdicionalesConCantidad(servicios);

        when(reservaRepo.findById(reservaId)).thenReturn(Optional.of(reserva));

        // Act
        FacturaDTO factura = facturaService.generarFacturaParaReserva(reservaId);

        // Assert
        assertNotNull(factura);
        assertNull(factura.getFacturaId());
        assertEquals(reservaId, factura.getReservaId());
        assertEquals("John Doe", factura.getNombrePasajero());
        assertEquals(LocalDate.now(), factura.getFechaEmision());
        assertEquals(1, factura.getItems().size());

        ItemFacturaDTO item = factura.getItems().get(0);

        assertEquals("SPA", item.getNombreServicio());
        assertEquals(2, item.getCantidadTotal());
        assertEquals(50.0, item.getPrecioUnitario());
        assertEquals(100.0, item.getSubTotal());
        assertEquals(100.0, factura.getTotal());

        verify(reservaRepo, times(1)).findById(reservaId);
    }

    @Test
    void shouldThrowExceptionWhenReservaNotFound() {
        // Arrange
        Long reservaId = 2L;
        when(reservaRepo.findById(reservaId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> facturaService.generarFacturaParaReserva(reservaId));
        assertEquals("Reserva no encontrada: 2", exception.getMessage());

        verify(reservaRepo, times(1)).findById(reservaId);
    }


    @Test
    void shouldHandleEmptyServiciosAdicionales() {
        // Arrange
        Long reservaId = 3L;

        Usuario usuario = new Usuario();
        usuario.setNombre("Jane");
        usuario.setApellido("Smith");

        Pasajero pasajero = new Pasajero();
        pasajero.setUsuario(usuario);

        Reserva reserva = new Reserva();
        reserva.setId(reservaId);
        reserva.setPasajero(pasajero);
        reserva.setServiciosAdicionalesConCantidad(new HashMap<>());

        when(reservaRepo.findById(reservaId)).thenReturn(Optional.of(reserva));

        // Act
        FacturaDTO factura = facturaService.generarFacturaParaReserva(reservaId);

        // Assert
        assertNotNull(factura);
        assertNull(factura.getFacturaId());
        assertEquals(reservaId, factura.getReservaId());
        assertEquals("Jane Smith", factura.getNombrePasajero());
        assertEquals(LocalDate.now(), factura.getFechaEmision());
        assertTrue(factura.getItems().isEmpty());
        assertEquals(0.0, factura.getTotal());

        verify(reservaRepo, times(1)).findById(reservaId);
    }
}