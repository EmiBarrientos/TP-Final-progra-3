package com.example.demo.service;

import com.example.demo.dto.CostoHabitacionDTO;
import com.example.demo.mapper.CostoHabitacionMapper;
import com.example.demo.model.CostoHabitacion;
import com.example.demo.model.enums.TipoHabitacion;
import com.example.demo.repository.CostoHabitacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CostoHabitacionService {
    private final CostoHabitacionRepository costoHabitacionRepository;
    private final CostoHabitacionMapper costoHabitacionMapper;

    public List<CostoHabitacionDTO> findAll() {
        return costoHabitacionRepository.findAll()
                .stream().map(p->costoHabitacionMapper.toDto(p)).toList();
    }

    public Optional<CostoHabitacionDTO> findById(Long id) {
        CostoHabitacionDTO costoHabitacionDTO = costoHabitacionMapper.toDto(
                costoHabitacionRepository.findById(id).get()
        );

        return Optional.ofNullable(costoHabitacionDTO);
    }

    public Optional<CostoHabitacionDTO> save(CostoHabitacionDTO costoHabitacionDTO) {
        CostoHabitacion costoHabitacion = costoHabitacionMapper.toEntity(costoHabitacionDTO);
        costoHabitacionDTO = costoHabitacionMapper.toDto(
                costoHabitacionRepository.save(costoHabitacion) );
        return Optional.ofNullable(costoHabitacionDTO);
    }

    public void deleteById(Long id) {
        costoHabitacionRepository.deleteById(id);
    }

    public Optional <CostoHabitacionDTO> findByTipoHabitacion(String tipo) {
        TipoHabitacion tipoEnum = TipoHabitacion.valueOf(tipo.toUpperCase());
        CostoHabitacion costoHabitacion = costoHabitacionRepository.findByTipoHabitacion(tipoEnum);
        CostoHabitacionDTO costoHabitacionDTO = costoHabitacionMapper.toDto(costoHabitacion);
        return Optional.ofNullable(costoHabitacionDTO);
    }
}