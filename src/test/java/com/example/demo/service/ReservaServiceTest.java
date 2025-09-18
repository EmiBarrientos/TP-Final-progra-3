package com.example.demo.service;

import com.example.demo.dto.ReservaDTO;
import com.example.demo.dto.crear.ReservaCrearDTO;
import com.example.demo.mapper.ReservaMapper;
import com.example.demo.mapper.noIdenticos.ReservaCrearMapper;
import com.example.demo.model.Habitacion;
import com.example.demo.model.Reserva;
import com.example.demo.model.enums.EstadoReserva;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.service.ReservaService;
import com.example.demo.service.exepciones.DatosInvalidosReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ReservaMapper reservaMapper;

    @Mock
    private ReservaCrearMapper reservaCrearMapper;

    @InjectMocks
    private ReservaService reservaService;

    private Reserva reserva;
    private ReservaDTO reservaDTO;
    private ReservaCrearDTO reservaCrearDTO;
    private Habitacion habitacion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        habitacion = new Habitacion();
        habitacion.setCapacidad(3);

        reserva = new Reserva();
        reserva.setId(1L);
        reserva.setHabitacion(habitacion);

        reservaDTO = new ReservaDTO();
        reservaCrearDTO = new ReservaCrearDTO();
    }

    @Test
    void findAll_shouldReturnList() {
        when(reservaRepository.findAll()).thenReturn(List.of(reserva));
        when(reservaMapper.toDto(reserva)).thenReturn(reservaDTO);

        List<ReservaDTO> result = reservaService.findAll();

        assertEquals(1, result.size());
        verify(reservaRepository).findAll();
    }

    @Test
    void findById_shouldReturnReservaDTO() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(reservaMapper.toDto(reserva)).thenReturn(reservaDTO);

        Optional<ReservaDTO> result = reservaService.findById(1L);

        assertTrue(result.isPresent());
        verify(reservaRepository).findById(1L);
    }

    @Test
    void save_shouldThrowExceptionIfCantidadIsEmpty() {
        when(reservaCrearMapper.toEntity(reservaCrearDTO)).thenReturn(reserva);
        when(reservaCrearMapper.toCantidadPasajeros(reservaCrearDTO)).thenReturn(Optional.empty());

        assertThrows(DatosInvalidosReserva.class, () -> reservaService.save(reservaCrearDTO));
    }

    @Test
    void save_shouldThrowExceptionIfCantidadExceedsCapacidad() {
        when(reservaCrearMapper.toEntity(reservaCrearDTO)).thenReturn(reserva);
        when(reservaCrearMapper.toCantidadPasajeros(reservaCrearDTO)).thenReturn(Optional.of(5)); // > 3

        assertThrows(DatosInvalidosReserva.class, () -> reservaService.save(reservaCrearDTO));
    }

    @Test
    void save_shouldReturnSavedReserva() {
        when(reservaCrearMapper.toEntity(reservaCrearDTO)).thenReturn(reserva);
        when(reservaCrearMapper.toCantidadPasajeros(reservaCrearDTO)).thenReturn(Optional.of(2));
        when(reservaRepository.save(reserva)).thenReturn(reserva);

        Optional<Reserva> result = reservaService.save(reservaCrearDTO);

        assertTrue(result.isPresent());
        verify(reservaRepository).save(reserva);
    }

    @Test
    void deleteById_shouldCallRepository() {
        reservaService.deleteById(1L);
        verify(reservaRepository).deleteById(1L);
    }

    @Test
    void findByFechaInicioBetween_shouldReturnList() {
        LocalDate ini = LocalDate.now();
        LocalDate fin = ini.plusDays(5);

        when(reservaRepository.findByFechaInicioBetween(ini, fin)).thenReturn(List.of(reserva));
        when(reservaMapper.toDto(reserva)).thenReturn(reservaDTO);

        List<ReservaDTO> result = reservaService.findByFechaInicioBetween(ini, fin);

        assertEquals(1, result.size());
    }

    @Test
    void findByPasajeroId_shouldReturnList() {
        when(reservaRepository.findByPasajeroId(10L)).thenReturn(List.of(reserva));
        when(reservaMapper.toDto(reserva)).thenReturn(reservaDTO);

        List<ReservaDTO> result = reservaService.findByPasajeroId(10L);

        assertEquals(1, result.size());
    }

    @Test
    void findByHabitacionId_shouldReturnList() {
        when(reservaRepository.findByHabitacionId(99L)).thenReturn(List.of(reserva));
        when(reservaMapper.toDto(reserva)).thenReturn(reservaDTO);

        List<ReservaDTO> result = reservaService.findByHabitacionId(99L);

        assertEquals(1, result.size());
    }

    @Test
    void obtenerHabitacionesReservadas_shouldReturnListOfIds() {
        LocalDate ini = LocalDate.now();
        LocalDate fin = ini.plusDays(1);

        when(reservaRepository.findHabitacionIdsReservadasEntreFechas(ini, fin)).thenReturn(List.of(1L, 2L));

        List<Long> ids = reservaService.obtenerHabitacionesReservadas(ini, fin);

        assertEquals(2, ids.size());
        verify(reservaRepository).findHabitacionIdsReservadasEntreFechas(ini, fin);
    }

    @Test
    void findByEstado_shouldReturnList() {
        when(reservaRepository.findByEstado(EstadoReserva.CONFIRMADA)).thenReturn(List.of(reserva));
        when(reservaMapper.toDto(reserva)).thenReturn(reservaDTO);

        List<ReservaDTO> result = reservaService.findByEstado(EstadoReserva.CONFIRMADA);

        assertEquals(1, result.size());
    }

    @Test
    void updateReserva_shouldUpdateWhenExists() {
        when(reservaCrearMapper.toEntity(reservaCrearDTO)).thenReturn(reserva);
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(reservaMapper.toDto(reserva)).thenReturn(reservaDTO);
        when(reservaRepository.save(reserva)).thenReturn(reserva);

        Optional<ReservaDTO> result = reservaService.updateReserva(1L, reservaCrearDTO);

        assertTrue(result.isPresent());
        verify(reservaRepository).save(reserva);
    }

    @Test
    void updateReserva_shouldReturnEmptyWhenNotFound() {
        when(reservaCrearMapper.toEntity(reservaCrearDTO)).thenReturn(reserva);
        when(reservaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ReservaDTO> result = reservaService.updateReserva(1L, reservaCrearDTO);

        assertTrue(result.isEmpty());
    }
}
