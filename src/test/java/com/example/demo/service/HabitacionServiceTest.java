

package com.example.demo.service;

import com.example.demo.dto.HabitacionDTO;
import com.example.demo.dto.crear.HabitacionCrearDTO;
import com.example.demo.mapper.HabitacionMapper;
import com.example.demo.mapper.noIdenticos.HabitacionCrearMapper;
import com.example.demo.model.Habitacion;
import com.example.demo.model.enums.EstadoHabitacion;
import com.example.demo.model.enums.TipoHabitacion;
import com.example.demo.repository.HabitacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HabitacionServiceTest {

    @Mock
    private HabitacionRepository habitacionRepository;

    @Mock
    private HabitacionMapper habitacionMapper;

    @Mock
    private HabitacionCrearMapper habitacionCrearMapper;

    @InjectMocks
    private HabitacionService habitacionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Habitacion habitacion = new Habitacion();
        HabitacionDTO dto = new HabitacionDTO();
        when(habitacionRepository.findAll()).thenReturn(List.of(habitacion));
        when(habitacionMapper.toDto(habitacion)).thenReturn(dto);

        List<HabitacionDTO> result = habitacionService.findAll();

        assertEquals(1, result.size());
        verify(habitacionRepository).findAll();
    }

    @Test
    void testFindByIdFound() {
        Long id = 1L;
        Habitacion habitacion = new Habitacion();
        HabitacionDTO dto = new HabitacionDTO();
        when(habitacionRepository.findById(id)).thenReturn(Optional.of(habitacion));
        when(habitacionMapper.toDto(habitacion)).thenReturn(dto);

        Optional<HabitacionDTO> result = habitacionService.findById(id);

        assertTrue(result.isPresent());
        verify(habitacionRepository).findById(id);
    }

    @Test
    void testSave() {
        HabitacionCrearDTO crearDTO = new HabitacionCrearDTO();
        Habitacion habitacion = new Habitacion();
        HabitacionDTO dto = new HabitacionDTO();

        when(habitacionCrearMapper.toEntity(crearDTO)).thenReturn(habitacion);
        when(habitacionRepository.save(habitacion)).thenReturn(habitacion);
        when(habitacionMapper.toDto(habitacion)).thenReturn(dto);

        Optional<HabitacionDTO> result = habitacionService.save(crearDTO);

        assertTrue(result.isPresent());
        verify(habitacionRepository).save(habitacion);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        doNothing().when(habitacionRepository).deleteById(id);

        habitacionService.deleteById(id);

        verify(habitacionRepository).deleteById(id);
    }

    @Test
    void testFindByEstado() {
        EstadoHabitacion estado = EstadoHabitacion.DISPONIBLE;
        Habitacion habitacion = new Habitacion();
        HabitacionDTO dto = new HabitacionDTO();

        when(habitacionRepository.findByEstado(estado)).thenReturn(List.of(habitacion));
        when(habitacionMapper.toDto(habitacion)).thenReturn(dto);

        List<HabitacionDTO> result = habitacionService.findByEstado("DISPONIBLE");

        assertEquals(1, result.size());
    }

    @Test
    void testFindByTipoHabitacion() {
        TipoHabitacion tipo = TipoHabitacion.SIMPLE;
        Habitacion habitacion = new Habitacion();
        HabitacionDTO dto = new HabitacionDTO();

        when(habitacionRepository.findByTipoHabitacion(tipo)).thenReturn(List.of(habitacion));
        when(habitacionMapper.toDto(habitacion)).thenReturn(dto);

        List<HabitacionDTO> result = habitacionService.findByTipoHabitacion("SIMPLE");

        assertEquals(1, result.size());
    }

    @Test
    void testFindByNumeroHabitacion() {
        String numero = "101";
        Habitacion habitacion = new Habitacion();
        HabitacionDTO dto = new HabitacionDTO();

        when(habitacionRepository.findByNumeroHabitacion(numero)).thenReturn(habitacion);
        when(habitacionMapper.toDto(habitacion)).thenReturn(dto);

        Optional<HabitacionDTO> result = habitacionService.findByNumeroHabitacion(numero);

        assertTrue(result.isPresent());
    }

    @Test
    void testUpdateHabitacion_WhenExists() {
        Long id = 1L;
        HabitacionCrearDTO dtoCrear = new HabitacionCrearDTO();
        dtoCrear.setId(id);
        Habitacion existing = new Habitacion();
        Habitacion updates = new Habitacion();
        HabitacionDTO dto = new HabitacionDTO();

        when(habitacionCrearMapper.toEntity(dtoCrear)).thenReturn(updates);
        when(habitacionRepository.findById(id)).thenReturn(Optional.of(existing));
        when(habitacionRepository.save(existing)).thenReturn(existing);
        when(habitacionMapper.toDto(existing)).thenReturn(dto);

        Optional<HabitacionDTO> result = habitacionService.updateHabitacion(id, dtoCrear);

        assertTrue(result.isPresent());
    }

    @Test
    void testUpdateHabitacion_WhenNotExists() {
        Long id = 1L;
        HabitacionCrearDTO dtoCrear = new HabitacionCrearDTO();
        dtoCrear.setId(id);

        when(habitacionRepository.findById(id)).thenReturn(Optional.empty());

        Optional<HabitacionDTO> result = habitacionService.updateHabitacion(id, dtoCrear);

        assertFalse(result.isPresent());
    }
}
