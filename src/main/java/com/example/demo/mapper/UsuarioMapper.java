package com.example.demo.mapper;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {


    public UsuarioDTO toDto(Usuario model) {
        if (model == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        ReflectionMapper.actualizarCamposNoNulos(model, dto);
        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario model = new Usuario();
        ReflectionMapper.actualizarCamposNoNulos(dto, model);
        return model;
    }

    }

