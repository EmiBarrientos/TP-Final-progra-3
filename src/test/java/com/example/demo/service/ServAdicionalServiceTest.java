package com.example.demo.service;

import com.example.demo.dto.ServAdicionalDTO;
import com.example.demo.model.ServAdicional;
import com.example.demo.model.enums.ServAdicionalEnum;
import com.example.demo.repository.ServAdicionalRepository;
import com.example.demo.service.ServAdicionalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServAdicionalServiceTest {

    @Mock
    private ServAdicionalRepository repo;

    @InjectMocks
    private ServAdicionalService service;

    private ServAdicional entidad;
    private ServAdicionalDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        entidad = new ServAdicional();
        entidad.setId(1L);
        entidad.setNombre(ServAdicionalEnum.DESAYUNO);
        entidad.setDescripcion("Desayuno buffet");

        dto = new ServAdicionalDTO(1L, "DESAYUNO", "Desayuno buffet");
    }

    @Test
    void findAllDtos_shouldReturnList() {
        when(repo.findAll()).thenReturn(List.of(entidad));

        List<ServAdicionalDTO> result = service.findAllDtos();

        assertEquals(1, result.size());
        assertEquals(dto.getNombre(), result.get(0).getNombre());
        verify(repo).findAll();
    }

    @Test
    void findDtoById_shouldReturnDTO() {
        when(repo.findById(1L)).thenReturn(Optional.of(entidad));

        Optional<ServAdicionalDTO> result = service.findDtoById(1L);

        assertTrue(result.isPresent());
        assertEquals(dto.getDescripcion(), result.get().getDescripcion());
        verify(repo).findById(1L);
    }

    @Test
    void findDtoById_shouldReturnEmptyIfNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        Optional<ServAdicionalDTO> result = service.findDtoById(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void saveDto_shouldSaveAndReturnDTO() {
        when(repo.save(any(ServAdicional.class))).thenReturn(entidad);

        ServAdicionalDTO result = service.saveDto(dto);

        assertNotNull(result);
        assertEquals(dto.getNombre(), result.getNombre());
        verify(repo).save(any(ServAdicional.class));
    }

    @Test
    void deleteById_shouldCallRepo() {
        service.deleteById(1L);
        verify(repo).deleteById(1L);
    }
}
