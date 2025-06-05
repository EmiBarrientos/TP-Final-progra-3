package com.example.demo.mapper;

import com.example.demo.dto.EmpleadoAccionReservaDTO;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.EmpleadoAccionReserva;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoAccionReservaMapper {
    public EmpleadoAccionReservaDTO toDto(EmpleadoAccionReserva model) {
        if (model == null) {
            return null;
        }
        EmpleadoAccionReservaDTO dto = new EmpleadoAccionReservaDTO();
        ReflectionMapper.actualizarCamposNoNulos(model, dto);
        return dto;
    }

    public EmpleadoAccionReserva toEntity(EmpleadoAccionReservaDTO dto) {
        if (dto == null) {
            return null;
        }
        EmpleadoAccionReserva model = new EmpleadoAccionReserva();
        ReflectionMapper.actualizarCamposNoNulos(dto, model);
        return model;
    }
}

