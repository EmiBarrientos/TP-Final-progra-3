package com.example.demo.service;

import com.example.demo.dto.crear.EmpleadoAccionReservaCrearDTO;
import com.example.demo.dto.EmpleadoAccionReservaDTO;
import com.example.demo.mapper.EmpleadoAccionReservaMapper;
import com.example.demo.mapper.noIdenticos.EmpleadoAccionReservaCrearMapper;
import com.example.demo.model.EmpleadoAccionReserva;
import com.example.demo.repository.EmpleadoAccionReservaRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmpleadoAccionReservaServiceTest {

    @Mock
    private EmpleadoAccionReservaRepository repository;

    @Mock
    private EmpleadoAccionReservaMapper mapper;

    @Mock
    private EmpleadoAccionReservaCrearMapper crearMapper;

    @InjectMocks
    private EmpleadoAccionReservaService service;

    public EmpleadoAccionReservaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateEmpleado_whenEmpleadoExists_updatesEmpleado() {
        Long id = 1L;
        EmpleadoAccionReservaCrearDTO dto = new EmpleadoAccionReservaCrearDTO();
        dto.setId(id);
        EmpleadoAccionReserva existingEmpleado = new EmpleadoAccionReserva();
        EmpleadoAccionReserva updatedEmpleado = new EmpleadoAccionReserva();
        EmpleadoAccionReservaDTO responseDTO = new EmpleadoAccionReservaDTO();

        when(repository.findById(id)).thenReturn(Optional.of(existingEmpleado));
        when(crearMapper.toEntity(dto)).thenReturn(updatedEmpleado);
        when(repository.save(any(EmpleadoAccionReserva.class))).thenReturn(updatedEmpleado);
        when(mapper.toDto(updatedEmpleado)).thenReturn(responseDTO);

        Optional<EmpleadoAccionReservaDTO> result = service.updateEmpleado(id, dto);

        assertTrue(result.isPresent());
        assertEquals(responseDTO, result.get());
        verify(repository).findById(id);
        verify(repository).save(updatedEmpleado);
        verify(mapper).toDto(updatedEmpleado);
    }

    @Test
    void updateEmpleado_whenEmpleadoDoesNotExist_returnsEmptyOptional() {
        Long id = 1L;
        EmpleadoAccionReservaCrearDTO dto = new EmpleadoAccionReservaCrearDTO();
        dto.setId(id);

        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<EmpleadoAccionReservaDTO> result = service.updateEmpleado(id, dto);

        assertFalse(result.isPresent());
        verify(repository).findById(id);
        verifyNoInteractions(crearMapper);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    void updateEmpleado_withInvalidDTO_throwsConstraintViolationException() {
        Long id = 1L;
        EmpleadoAccionReservaCrearDTO dto = new EmpleadoAccionReservaCrearDTO();
        dto.setId(id);
        EmpleadoAccionReserva invalidEntity = new EmpleadoAccionReserva();

        when(crearMapper.toEntity(dto)).thenReturn(invalidEntity);
        when(repository.findById(id)).thenReturn(Optional.of(invalidEntity));
        doThrow(ConstraintViolationException.class).when(repository).save(invalidEntity);

        assertThrows(ConstraintViolationException.class, () -> service.updateEmpleado(id, dto));
    }
    @Test
    void findAll_shouldReturnListOfDTOs() {
        EmpleadoAccionReserva entity = new EmpleadoAccionReserva();
        EmpleadoAccionReservaDTO dto = new EmpleadoAccionReservaDTO();

        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        List<EmpleadoAccionReservaDTO> result = service.findAll();

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
        verify(repository).findAll();
    }

    @Test
    void findById_whenExists_shouldReturnDTO() {
        Long id = 1L;
        EmpleadoAccionReserva entity = new EmpleadoAccionReserva();
        EmpleadoAccionReservaDTO dto = new EmpleadoAccionReservaDTO();

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        Optional<EmpleadoAccionReservaDTO> result = service.findById(id);

        assertTrue(result.isPresent());
        assertEquals(dto, result.get());
        verify(repository).findById(id);
    }

    @Test
    void findById_whenNotExists_shouldReturnEmpty() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<EmpleadoAccionReservaDTO> result = service.findById(id);

        assertFalse(result.isPresent());
        verify(repository).findById(id);
    }

    @Test
    void save_shouldReturnSavedDTO() {
        EmpleadoAccionReservaCrearDTO dtoCrear = new EmpleadoAccionReservaCrearDTO();
        EmpleadoAccionReserva entity = new EmpleadoAccionReserva();
        EmpleadoAccionReserva savedEntity = new EmpleadoAccionReserva();
        EmpleadoAccionReservaDTO dto = new EmpleadoAccionReservaDTO();

        when(crearMapper.toEntity(dtoCrear)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDto(savedEntity)).thenReturn(dto);

        Optional<EmpleadoAccionReservaDTO> result = service.save(dtoCrear);

        assertTrue(result.isPresent());
        assertEquals(dto, result.get());
        verify(repository).save(entity);
    }

    @Test
    void deleteById_shouldCallRepositoryDelete() {
        Long id = 1L;
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository).deleteById(id);
    }
}