package com.example.demo.service;
import com.example.demo.dto.PasajeroDTO;
import com.example.demo.dto.crear.PasajeroConUsuarioCreadoCrearDTO;
import com.example.demo.mapper.PasajeroMapper;
import com.example.demo.mapper.noIdenticos.PasajeroConUsuarioCreadoCrearMapper;
import com.example.demo.model.Pasajero;
import com.example.demo.repository.PasajeroRepository;
import com.example.demo.service.PasajeroService;
import com.example.demo.mapper.util.ReflectionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PasajeroServiceTest {

    @Mock
    private PasajeroRepository pasajeroRepository;

    @Mock
    private PasajeroMapper pasajeroMapper;

    @Mock
    private PasajeroConUsuarioCreadoCrearMapper pasajeroConUsuarioCreadoCrearMapper;

    @InjectMocks
    private PasajeroService pasajeroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Pasajero> pasajeros = List.of(new Pasajero(), new Pasajero());
        when(pasajeroRepository.findAll()).thenReturn(pasajeros);

        List<Pasajero> result = pasajeroService.findAll();

        assertEquals(2, result.size());
        verify(pasajeroRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Pasajero pasajero = new Pasajero();
        PasajeroDTO pasajeroDTO = new PasajeroDTO();
        when(pasajeroRepository.findById(1L)).thenReturn(Optional.of(pasajero));
        when(pasajeroMapper.toDto(pasajero)).thenReturn(pasajeroDTO);

        Optional<PasajeroDTO> result = pasajeroService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(pasajeroDTO, result.get());
    }

    @Test
    void testSave() {
        PasajeroConUsuarioCreadoCrearDTO crearDTO = new PasajeroConUsuarioCreadoCrearDTO();
        Pasajero pasajero = new Pasajero();
        Pasajero savedPasajero = new Pasajero();
        PasajeroDTO pasajeroDTO = new PasajeroDTO();

        when(pasajeroConUsuarioCreadoCrearMapper.toEntity(crearDTO)).thenReturn(pasajero);
        when(pasajeroRepository.save(pasajero)).thenReturn(savedPasajero);
        when(pasajeroMapper.toDto(savedPasajero)).thenReturn(pasajeroDTO);

        Optional<PasajeroDTO> result = pasajeroService.save(crearDTO);

        assertTrue(result.isPresent());
        assertEquals(pasajeroDTO, result.get());
    }

    @Test
    void testDeleteById() {
        pasajeroService.deleteById(1L);
        verify(pasajeroRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdatePasajeroExists() {
        Long id = 1L;
        PasajeroConUsuarioCreadoCrearDTO crearDTO = new PasajeroConUsuarioCreadoCrearDTO();
        Pasajero modelCrearDetails = new Pasajero();
        Pasajero existingPasajero = new Pasajero();
        Pasajero updatedPasajero = new Pasajero();
        PasajeroDTO pasajeroDTO = new PasajeroDTO();

        when(pasajeroConUsuarioCreadoCrearMapper.toEntity(crearDTO)).thenReturn(modelCrearDetails);
        when(pasajeroRepository.findById(id)).thenReturn(Optional.of(existingPasajero));
        when(pasajeroRepository.save(existingPasajero)).thenReturn(updatedPasajero);
        when(pasajeroMapper.toDto(updatedPasajero)).thenReturn(pasajeroDTO);

        Optional<PasajeroDTO> result = pasajeroService.updatePasajero(id, crearDTO);

        assertTrue(result.isPresent());
        assertEquals(pasajeroDTO, result.get());
    }

    @Test
    void testUpdatePasajeroNotFound() {
        Long id = 1L;
        PasajeroConUsuarioCreadoCrearDTO crearDTO = new PasajeroConUsuarioCreadoCrearDTO();
        when(pasajeroRepository.findById(id)).thenReturn(Optional.empty());

        Optional<PasajeroDTO> result = pasajeroService.updatePasajero(id, crearDTO);

        assertTrue(result.isEmpty());
    }
}
