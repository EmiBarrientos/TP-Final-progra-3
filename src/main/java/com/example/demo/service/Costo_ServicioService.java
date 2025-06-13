package com.example.demo.service;

import com.example.demo.dto.CostoServicioDTO;
import com.example.demo.mapper.CostoServicioMapper;
import com.example.demo.model.Costo_Servicio;
import com.example.demo.model.enums.ServicioEnum;
import com.example.demo.repository.Costo_ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Costo_ServicioService {
    private final Costo_ServicioRepository costoServicioRepository;
    private final CostoServicioMapper costoServicioMapper;

    @Autowired
    public Costo_ServicioService(Costo_ServicioRepository costoServicioRepository, CostoServicioMapper costoServicioMapper) {
        this.costoServicioRepository = costoServicioRepository;
        this.costoServicioMapper = costoServicioMapper;
    }

    public List<CostoServicioDTO> findAll() {
        return costoServicioRepository.findAll()
                .stream().map(p->costoServicioMapper.toDto(p)).toList();
    }

    public Optional<CostoServicioDTO> findById(Long id) {
        CostoServicioDTO costoServicioDTO = costoServicioMapper.toDto(costoServicioRepository.findById(id).get());
        return Optional.ofNullable(costoServicioDTO);
    }

    public Optional<CostoServicioDTO> save(CostoServicioDTO costoServicioDTO) {
        Costo_Servicio costoServicio = costoServicioMapper.toEntity(costoServicioDTO);
        costoServicioDTO = costoServicioMapper.toDto(costoServicioRepository.save(costoServicio));
        return Optional.ofNullable(costoServicioDTO);
    }

    public void deleteById(Long id) {
        costoServicioRepository.deleteById(id);
    }

    public Optional<CostoServicioDTO> findByNombre(String nombre) {
        ServicioEnum nombreEnum = ServicioEnum.valueOf(nombre.toUpperCase());
        CostoServicioDTO costoServicioDTO = costoServicioMapper.toDto(costoServicioRepository.findByNombre(nombreEnum));
        return Optional.ofNullable(costoServicioDTO);
    }
}