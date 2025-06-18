package com.example.demo.service;
import com.example.demo.dto.CostoHabitacionDTO;
import com.example.demo.mapper.CostoHabitacionMapper;
import com.example.demo.model.CostoHabitacion;
import com.example.demo.model.enums.TipoHabitacion;
import com.example.demo.repository.CostoHabitacionRepository;
import com.example.demo.service.CostoHabitacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CostoHabitacionServiceTest {

    @Mock
    private CostoHabitacionRepository repository;

    @Mock
    private CostoHabitacionMapper mapper;

    @InjectMocks
    private CostoHabitacionService service;

    private CostoHabitacion costoHabitacion;
    private CostoHabitacionDTO costoHabitacionDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        costoHabitacion = new CostoHabitacion();
        costoHabitacionDTO = new CostoHabitacionDTO();
    }

    @Test
    void findAll_shouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(costoHabitacion));
        when(mapper.toDto(costoHabitacion)).thenReturn(costoHabitacionDTO);

        List<CostoHabitacionDTO> result = service.findAll();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    void findById_shouldReturnOptional() {
        when(repository.findById(1L)).thenReturn(Optional.of(costoHabitacion));
        when(mapper.toDto(costoHabitacion)).thenReturn(costoHabitacionDTO);

        Optional<CostoHabitacionDTO> result = service.findById(1L);

        assertTrue(result.isPresent());
        verify(repository).findById(1L);
    }

    @Test
    void save_shouldReturnSavedDTO() {
        when(mapper.toEntity(costoHabitacionDTO)).thenReturn(costoHabitacion);
        when(repository.save(costoHabitacion)).thenReturn(costoHabitacion);
        when(mapper.toDto(costoHabitacion)).thenReturn(costoHabitacionDTO);

        Optional<CostoHabitacionDTO> result = service.save(costoHabitacionDTO);

        assertTrue(result.isPresent());
        verify(repository).save(costoHabitacion);
    }

    @Test
    void deleteById_shouldCallRepository() {
        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void findByTipoHabitacion_shouldReturnOptional() {
        TipoHabitacion tipo = TipoHabitacion.SIMPLE;
        when(repository.findByTipoHabitacion(tipo)).thenReturn(costoHabitacion);
        when(mapper.toDto(costoHabitacion)).thenReturn(costoHabitacionDTO);

        Optional<CostoHabitacionDTO> result = service.findByTipoHabitacion("SIMPLE");

        assertTrue(result.isPresent());
        verify(repository).findByTipoHabitacion(tipo);
    }
}
