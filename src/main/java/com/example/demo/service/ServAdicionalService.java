package com.example.demo.service;

import com.example.demo.dto.ServAdicionalDTO;
import com.example.demo.model.ServAdicional;
import com.example.demo.model.enums.ServAdicionalEnum;
import com.example.demo.repository.ServAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServAdicionalService {

    private final ServAdicionalRepository repo;

    @Autowired
    public ServAdicionalService(ServAdicionalRepository repo) {
        this.repo = repo;
    }

    // 1) Conversión Entidad → DTO
    private ServAdicionalDTO entityToDto(ServAdicional entidad) {
        return new ServAdicionalDTO(
                entidad.getId(),
                entidad.getNombre().name(),
                entidad.getDescripcion()
        );
    }

    // 2) Conversión DTO → Entidad
    private ServAdicional dtoToEntity(ServAdicionalDTO dto) {
        ServAdicional entidad = new ServAdicional();
        entidad.setId(dto.getId());         // si es null → JPA INSERT; si no → UPDATE
        entidad.setNombre(ServAdicionalEnum.valueOf(dto.getNombre()));
        entidad.setDescripcion(dto.getDescripcion());
        return entidad;
    }

    // 3) Listar todos (devuelve lista de DTOs)
    public List<ServAdicionalDTO> findAllDtos() {
        List<ServAdicional> entidades = repo.findAll();
        return entidades.stream()
                .map(this::entityToDto)
                .toList();
    }

    // 4) Buscar por ID (Optional<DTO>)
    public Optional<ServAdicionalDTO> findDtoById(Long id) {
        return repo.findById(id)
                .map(this::entityToDto);
    }

    // 5) Guardar (crear o actualizar) a partir de DTO
    public ServAdicionalDTO saveDto(ServAdicionalDTO dto) {
        ServAdicional entidad = dtoToEntity(dto);
        ServAdicional guardado = repo.save(entidad);
        return entityToDto(guardado);
    }

    // 6) Eliminar por ID
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
