package com.example.demo.service;

import com.example.demo.dto.CostoServAdicionalDTO;
import com.example.demo.mapper.CostoServAdicionalMapper;
import com.example.demo.model.CostoServAdicional;
import com.example.demo.model.enums.ServAdicionalEnum;
import com.example.demo.repository.CostoServAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VERCostoServAdicionalService {

    @Autowired
    private CostoServAdicionalRepository costoRepo;
    @Autowired
    private CostoServAdicionalMapper costoMapper;

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
    // se usa el mapper toDTO
/*    private CostoServAdicionalDTO toDTO(CostoServAdicional entidad) {
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
    }*/

    /**
     * 1) Retorna todos los registros como lista de DTOs.
     */

    public List<CostoServAdicionalDTO> findAllDTO() {
        return costoRepo.findAll()
                .stream()
                .map(p->costoMapper.toDto(p))
                .toList();
    }

    /**
     * 2) Busca uno por ID y lo convierte a DTO.
     */
    public Optional<CostoServAdicionalDTO> findByIdDTO(Long id) {
        return Optional.ofNullable(costoMapper.toDto(costoRepo.findById(id).get()));
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
                    dtoRequest.getNombreServicioAdicional().toUpperCase()
            );
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Nombre de servicio inválido: "
                    + dtoRequest.getNombreServicioAdicional());
        }

        // Si dtoRequest.getId() no es null, busco la entidad existente; si no, creo una nueva
        CostoServAdicional entidad = dtoRequest.getId() != null
                ? costoRepo.findById(dtoRequest.getId()).orElse(new CostoServAdicional())
                : new CostoServAdicional();

        entidad.setNombre(enumNombre);
        entidad.setPrecio(dtoRequest.getPrecioUnitario());

        CostoServAdicional guardada = costoRepo.save(entidad);
        return costoMapper.toDto(guardada);
    }
}
