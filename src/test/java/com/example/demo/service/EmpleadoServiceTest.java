

package com.example.demo.service;

import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.dto.crear.EmpleadoConUsuarioCreadoCrearDTO;
import com.example.demo.mapper.EmpleadoMapper;
import com.example.demo.mapper.noIdenticos.EmpleadoConUsuarioCreadoCrearMapper;
import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRepository;
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

class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @Mock
    private EmpleadoMapper empleadoMapper;

    @Mock
    private EmpleadoConUsuarioCreadoCrearMapper empleadoConUsuarioCreadoCrearMapper;

    @InjectMocks
    private EmpleadoService empleadoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Empleado empleado = new Empleado();
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        when(empleadoRepository.findAll()).thenReturn(Arrays.asList(empleado));
        when(empleadoMapper.toDto(empleado)).thenReturn(empleadoDTO);

        List<EmpleadoDTO> result = empleadoService.findAll();

        assertEquals(1, result.size());
        verify(empleadoRepository).findAll();
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Empleado empleado = new Empleado();
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        when(empleadoRepository.findById(id)).thenReturn(Optional.of(empleado));
        when(empleadoMapper.toDto(empleado)).thenReturn(empleadoDTO);

        Optional<EmpleadoDTO> result = empleadoService.findById(id);

        assertTrue(result.isPresent());
        verify(empleadoRepository).findById(id);
    }

    @Test
    void testSave() {
        EmpleadoConUsuarioCreadoCrearDTO dto = new EmpleadoConUsuarioCreadoCrearDTO();
        Empleado empleado = new Empleado();
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();

        when(empleadoConUsuarioCreadoCrearMapper.toEntity(dto)).thenReturn(empleado);
        when(empleadoRepository.save(empleado)).thenReturn(empleado);
        when(empleadoMapper.toDto(empleado)).thenReturn(empleadoDTO);

        Optional<EmpleadoDTO> result = empleadoService.save(dto);

        assertTrue(result.isPresent());
        verify(empleadoRepository).save(empleado);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        doNothing().when(empleadoRepository).deleteById(id);

        empleadoService.deleteById(id);

        verify(empleadoRepository).deleteById(id);
    }

    @Test
    void testUpdateEmpledo_WhenExists() {
        Long id = 1L;
        EmpleadoConUsuarioCreadoCrearDTO dto = new EmpleadoConUsuarioCreadoCrearDTO();
        Empleado existingEmpleado = new Empleado();
        Empleado updatedEmpleado = new Empleado();
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();

        when(empleadoConUsuarioCreadoCrearMapper.toEntity(dto)).thenReturn(updatedEmpleado);
        when(empleadoRepository.findById(id)).thenReturn(Optional.of(existingEmpleado));
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(updatedEmpleado);
        when(empleadoMapper.toDto(updatedEmpleado)).thenReturn(empleadoDTO);

        Optional<EmpleadoDTO> result = empleadoService.updateEmpledo(id, dto);

        assertTrue(result.isPresent());
        verify(empleadoRepository).save(any(Empleado.class));
    }

    @Test
    void testUpdateEmpledo_WhenNotExists() {
        Long id = 1L;
        EmpleadoConUsuarioCreadoCrearDTO dto = new EmpleadoConUsuarioCreadoCrearDTO();

        when(empleadoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<EmpleadoDTO> result = empleadoService.updateEmpledo(id, dto);

        assertFalse(result.isPresent());
    }
}
