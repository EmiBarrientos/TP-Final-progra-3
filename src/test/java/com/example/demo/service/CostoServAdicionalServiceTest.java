package com.example.demo.service;


import com.example.demo.dto.CostoServAdicionalDTO;
import com.example.demo.mapper.CostoServAdicionalMapper;
import com.example.demo.model.CostoServAdicional;
import com.example.demo.model.enums.ServAdicionalEnum;
import com.example.demo.repository.CostoServAdicionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CostoServAdicionalServiceTest {

    @Mock
    private CostoServAdicionalRepository repo;

    @Mock
    private CostoServAdicionalMapper mapper;

    @InjectMocks
    private CostoServAdicionalService service;

    private CostoServAdicional entidad;
    private CostoServAdicionalDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        entidad = new CostoServAdicional();
        entidad.setId(1L);
        entidad.setNombre(ServAdicionalEnum.SPA);
        entidad.setPrecio(1000.0);

        dto = new CostoServAdicionalDTO(1L, ServAdicionalEnum.SPA, 1000.0);
    }

    @Test
    void findAll_shouldReturnListOfEntities() {
        when(repo.findAll()).thenReturn(List.of(entidad));

        List<CostoServAdicional> result = service.findAll();

        assertEquals(1, result.size());
        verify(repo).findAll();
    }

    @Test
    void findAllDTO_shouldReturnListOfDTOs() {
        when(repo.findAll()).thenReturn(List.of(entidad));
        when(mapper.toDto(entidad)).thenReturn(dto);

        List<CostoServAdicionalDTO> result = service.findAllDTO();

        assertEquals(1, result.size());
        assertEquals(ServAdicionalEnum.SPA, result.get(0).getNombre());
    }

    @Test
    void findById_shouldReturnOptionalEntity() {
        when(repo.findById(1L)).thenReturn(Optional.of(entidad));

        Optional<CostoServAdicional> result = service.findById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void findByIdDTO_shouldReturnDTO() {
        when(repo.findById(1L)).thenReturn(Optional.of(entidad));
        when(mapper.toDto(entidad)).thenReturn(dto);

        Optional<CostoServAdicionalDTO> result = service.findByIdDTO(1L);

        assertTrue(result.isPresent());
        assertEquals(dto.getPrecio(), result.get().getPrecio());
    }

    @Test
    void findByNombre_shouldReturnOptionalEntity() {
        when(repo.findByNombre(ServAdicionalEnum.SPA)).thenReturn(Optional.of(entidad));

        Optional<CostoServAdicional> result = service.findByNombre(ServAdicionalEnum.SPA);

        assertTrue(result.isPresent());
    }

    @Test
    void save_shouldPersistEntity() {
        when(repo.save(entidad)).thenReturn(entidad);

        CostoServAdicional result = service.save(entidad);

        assertNotNull(result);
        verify(repo).save(entidad);
    }

    @Test
    void deleteById_shouldCallRepo() {
        service.deleteById(1L);
        verify(repo).deleteById(1L);
    }

    @Test
    void saveFromDTO_shouldCreateNewWhenIdIsNull() {
        CostoServAdicionalDTO dtoNuevo = new CostoServAdicionalDTO(null, ServAdicionalEnum.SPA, 800.0);
        CostoServAdicional nuevaEntidad = new CostoServAdicional();
        nuevaEntidad.setNombre(ServAdicionalEnum.SPA);
        nuevaEntidad.setPrecio(800.0);

        when(repo.save(any(CostoServAdicional.class))).thenReturn(entidad);
        when(mapper.toDto(entidad)).thenReturn(dto);

        CostoServAdicionalDTO result = service.saveFromDTO(dtoNuevo);

        assertEquals(ServAdicionalEnum.SPA, result.getNombre());
        verify(repo).save(any(CostoServAdicional.class));
    }

    @Test
    void saveFromDTO_shouldUpdateWhenIdIsNotNull() {
        when(repo.findById(1L)).thenReturn(Optional.of(entidad));
        when(repo.save(entidad)).thenReturn(entidad);
        when(mapper.toDto(entidad)).thenReturn(dto);

        CostoServAdicionalDTO result = service.saveFromDTO(dto);

        assertEquals(1L, result.getId());
        verify(repo).save(entidad);
    }
}
