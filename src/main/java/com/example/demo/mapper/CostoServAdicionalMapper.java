package com.example.demo.mapper;

import com.example.demo.dto.CostoServAdicionalDTO;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.CostoServAdicional;
import org.springframework.stereotype.Component;

@Component
public class CostoServAdicionalMapper {
    public CostoServAdicionalDTO toDto(CostoServAdicional model) {
        if (model == null) {
            return null;
        }
        CostoServAdicionalDTO dto = new CostoServAdicionalDTO();
        ReflectionMapper.actualizarCamposNoNulos(model, dto);
        return dto;
    }

    public CostoServAdicional toEntity(CostoServAdicionalDTO dto) {
        if (dto == null) {
            return null;
        }
        CostoServAdicional model = new CostoServAdicional();
        ReflectionMapper.actualizarCamposNoNulos(dto, model);
        return model;
    }

    }

