package com.example.demo.service;

import com.example.demo.dto.CostoServicioDTO;
import com.example.demo.dto.crear.CostoServicioCrearDTO;
import com.example.demo.mapper.CostoServicioMapper;
import com.example.demo.mapper.noIdenticos.CostoServicioCrearMapper;
import com.example.demo.model.Costo_Servicio;
import com.example.demo.model.enums.ServicioEnum;
import com.example.demo.repository.Costo_ServicioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class Costo_ServicioServiceTest {

    @Mock
    private Costo_ServicioRepository costoServicioRepository;

    @Mock
    private CostoServicioMapper costoServicioMapper;

    @Mock
    private CostoServicioCrearMapper costoServicioCrearMapper;

    @InjectMocks
    private Costo_ServicioService costoServicioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedDto() {
        CostoServicioCrearDTO crearDTO = new CostoServicioCrearDTO();
        Costo_Servicio entity = new Costo_Servicio();
        CostoServicioDTO dto = new CostoServicioDTO();

        when(costoServicioCrearMapper.toEntity(crearDTO)).thenReturn(entity);
        when(costoServicioRepository.save(entity)).thenReturn(entity);
        when(costoServicioMapper.toDto(entity)).thenReturn(dto);

        Optional<CostoServicioDTO> result = costoServicioService.save(crearDTO);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(dto);
    }

    @Test
    void update_WhenIdExists_ShouldUpdateAndReturnDto() {
        Long id = 1L;
        CostoServicioCrearDTO crearDTO = new CostoServicioCrearDTO();
        Costo_Servicio entity = new Costo_Servicio();
        CostoServicioDTO dto = new CostoServicioDTO();

        when(costoServicioRepository.findById(id)).thenReturn(Optional.of(new Costo_Servicio()));
        when(costoServicioCrearMapper.toEntity(crearDTO)).thenReturn(entity);
        when(costoServicioRepository.save(entity)).thenReturn(entity);
        when(costoServicioMapper.toDto(entity)).thenReturn(dto);

        Optional<CostoServicioDTO> result = costoServicioService.update(id, crearDTO);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(dto);
    }

    @Test
    void update_WhenIdDoesNotExist_ShouldReturnEmpty() {
        Long id = 1L;
        when(costoServicioRepository.findById(id)).thenReturn(Optional.empty());

        Optional<CostoServicioDTO> result = costoServicioService.update(id, new CostoServicioCrearDTO());

        assertThat(result).isEmpty();
    }

    @Test
    void findAll_ShouldReturnListOfDtos() {
        Costo_Servicio entity = new Costo_Servicio();
        CostoServicioDTO dto = new CostoServicioDTO();

        when(costoServicioRepository.findAll()).thenReturn(List.of(entity));
        when(costoServicioMapper.toDto(entity)).thenReturn(dto);

        List<CostoServicioDTO> result = costoServicioService.findAll();

        assertThat(result).containsExactly(dto);
    }

    @Test
    void findById_ShouldReturnDto() {
        Long id = 1L;
        Costo_Servicio entity = new Costo_Servicio();
        CostoServicioDTO dto = new CostoServicioDTO();

        when(costoServicioRepository.findById(id)).thenReturn(Optional.of(entity));
        when(costoServicioMapper.toDto(entity)).thenReturn(dto);

        Optional<CostoServicioDTO> result = costoServicioService.findById(id);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(dto);
    }

    @Test
    void deleteById_ShouldCallRepository() {
        Long id = 1L;
        costoServicioService.deleteById(id);
        verify(costoServicioRepository).deleteById(id);
    }

    @Test
    void findByNombre_ShouldReturnDto() {
        String nombre = "TALLER";
        ServicioEnum servicioEnum = ServicioEnum.valueOf(nombre);
        Costo_Servicio entity = new Costo_Servicio();
        CostoServicioDTO dto = new CostoServicioDTO();

        when(costoServicioRepository.findByNombre(servicioEnum)).thenReturn(entity);
        when(costoServicioMapper.toDto(entity)).thenReturn(dto);

        Optional<CostoServicioDTO> result = costoServicioService.findByNombre(nombre);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(dto);
    }
}
