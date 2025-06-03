package com.example.demo.mapper;

import com.example.demo.dto.ReservaDTO;
import com.example.demo.dto.ServAdicionalDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.*;
import org.springframework.stereotype.Component;

@Component
    public class ReservaMapper {
        public ReservaDTO toDto(Reserva model) {
            if (model == null) {
                return null;
            }
            ReservaDTO dto = new ReservaDTO();
            ReflectionMapper.actualizarCamposNoNulos(model, dto);
            return dto;
        }

        public Reserva toEntity(ReservaDTO dto) {
            if (dto == null) {
                return null;
            }
            Reserva model = new Reserva();
            ReflectionMapper.actualizarCamposNoNulos(dto, model);
            return model;
        }

    }
