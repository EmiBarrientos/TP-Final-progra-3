package com.example.demo.mapper;

import com.example.demo.dto.PasajeroDTO;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.Pasajero;
import org.springframework.stereotype.Component;

@Component
public class PasajeroMapper {
    public PasajeroDTO toDto(Pasajero model) {
        if (model == null) {
            return null;
        }
        PasajeroDTO dto = new PasajeroDTO();
        ReflectionMapper.actualizarCamposNoNulos(model, dto);
        return dto;
    }

    public Pasajero toEntity(PasajeroDTO dto) {
        if (dto == null) {
            return null;
        }
        Pasajero model = new Pasajero();
        ReflectionMapper.actualizarCamposNoNulos(dto, model);
        return model;
    }
    }
