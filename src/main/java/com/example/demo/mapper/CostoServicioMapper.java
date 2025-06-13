package com.example.demo.mapper;

import com.example.demo.dto.CostoServicioDTO;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.Costo_Servicio;
import org.springframework.stereotype.Component;

@Component
    public class CostoServicioMapper {
        public CostoServicioDTO toDto(Costo_Servicio model) {
            if (model == null) {
                return null;
            }
            CostoServicioDTO dto = new CostoServicioDTO();
            ReflectionMapper.actualizarCamposNoNulos(model, dto);
            return dto;
        }

        public Costo_Servicio toEntity(CostoServicioDTO dto) {
            if (dto == null) {
                return null;
            }
            Costo_Servicio model = new Costo_Servicio();
            ReflectionMapper.actualizarCamposNoNulos(dto, model);
            return model;
        }
    }


