package com.example.demo.service;

import com.dream_team.proyecto_final_progra3.dto.CostoServAdicionalDTO;
import com.dream_team.proyecto_final_progra3.dto.ServicioDTO;
import com.dream_team.proyecto_final_progra3.entity.CostoServAdicional;
import com.dream_team.proyecto_final_progra3.entity.enums.ServAdicionalEnum;
import com.dream_team.proyecto_final_progra3.repository.CostoServAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CostoServAdicionalService {

    @Autowired
    private CostoServAdicionalRepository costoRepo;

    public List<CostoServAdicional> findAll() {
        return costoRepo.findAll();
    }

    public Optional<CostoServAdicional> findById(Long id) {
        return costoRepo.findById(id);
    }

    public Optional<CostoServAdicional> findByNombre(ServAdicionalEnum nombre) {
        return costoRepo.findByNombre(nombre);
    }

    public CostoServAdicional save(CostoServAdicional entidad) {
        return costoRepo.save(entidad);
    }

    public void deleteById(Long id) {
        costoRepo.deleteById(id);
    }

    /**
     * Método privado que mapea una entidad CostoServAdicional a su DTO.
     */
    private CostoServAdicionalDTO toDTO(CostoServAdicional entidad) {
        // Primero, construyo el ServicioDTO a partir del enum y el precio base
        ServicioDTO servicioDTO = new ServicioDTO(
                entidad.getId(),
                entidad.getNombre().name(),
                entidad.getPrecio()
        );

        // Luego, armo el CostoServAdicionalDTO con id, servicioDTO y precio unitario
        return new CostoServAdicionalDTO(
                entidad.getId(),
                servicioDTO,
                entidad.getPrecio()
        );
    }

    /**
     * 1) Retorna todos los registros como lista de DTOs.
     */
    public List<CostoServAdicionalDTO> findAllDTO() {
        return costoRepo.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * 2) Busca uno por ID y lo convierte a DTO.
     */
    public Optional<CostoServAdicionalDTO> findByIdDTO(Long id) {
        return costoRepo.findById(id)
                .map(this::toDTO);
    }

    /**
     * 3) Guarda o actualiza un registro a partir del DTO.
     *    Si el DTO trae id, intenta actualizar; si no, crea nuevo.
     */
    public CostoServAdicionalDTO saveFromDTO(CostoServAdicionalDTO dtoRequest) {
        // Convertir el nombre (String) a ServAdicionalEnum, para asignarlo a la entidad
        ServAdicionalEnum enumNombre;
        try {
            enumNombre = ServAdicionalEnum.valueOf(
                    dtoRequest.getNombreServicioAdicional()
                            .getNombre()
                            .toUpperCase()
            );
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Nombre de servicio inválido: "
                    + dtoRequest.getNombreServicioAdicional().getNombre());
        }

        // Si dtoRequest.getId() no es null, busco la entidad existente; si no, creo una nueva
        CostoServAdicional entidad = dtoRequest.getId() != null
                ? costoRepo.findById(dtoRequest.getId()).orElse(new CostoServAdicional())
                : new CostoServAdicional();

        entidad.setNombre(enumNombre);
        entidad.setPrecio(dtoRequest.getPrecioUnitario());

        CostoServAdicional guardada = costoRepo.save(entidad);
        return toDTO(guardada);
    }
}
