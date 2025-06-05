package com.example.demo.mapper;

import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {
    public EmpleadoDTO toDto(Empleado model) {
        if (model == null) {
            return null;
        }
        EmpleadoDTO dto = new EmpleadoDTO();
        ReflectionMapper.actualizarCamposNoNulos(model, dto);
        return dto;
    }

    public Empleado toEntity(EmpleadoDTO dto) {
        if (dto == null) {
            return null;
        }
        Empleado model = new Empleado();
        ReflectionMapper.actualizarCamposNoNulos(dto, model);
        return model;
    }
}

