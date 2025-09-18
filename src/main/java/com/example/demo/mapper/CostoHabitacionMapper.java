package com.example.demo.mapper;

import com.example.demo.dto.CostoHabitacionDTO;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.CostoHabitacion;
import org.springframework.stereotype.Component;

@Component
public class CostoHabitacionMapper {

    public CostoHabitacionDTO toDto(CostoHabitacion model) {
        if (model == null) {
            return null;
        }
        CostoHabitacionDTO dto = new CostoHabitacionDTO();
        ReflectionMapper.actualizarCamposNoNulos(model, dto);
        return dto;
    }

    public CostoHabitacion toEntity(CostoHabitacionDTO dto) {
        if (dto == null) {
            return null;
        }
        CostoHabitacion model = new CostoHabitacion();
        ReflectionMapper.actualizarCamposNoNulos(dto, model);
        return model;
    }
}
