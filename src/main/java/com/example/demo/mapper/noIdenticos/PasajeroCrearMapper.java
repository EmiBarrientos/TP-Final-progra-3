package com.example.demo.mapper.noIdenticos;

import com.example.demo.auth.dto.UsuarioDTO;
import com.example.demo.dto.crear.PasajeroCrearDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.model.Pasajero;
import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PasajeroCrearMapper {

    private final UsuarioService usuarioServicer;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public PasajeroCrearMapper(UsuarioService usuarioServicer, UsuarioMapper usuarioMapper) {
        this.usuarioServicer = usuarioServicer;
        this.usuarioMapper = usuarioMapper;
    }


    public PasajeroCrearDTO toDto(Pasajero model) {
        if (model == null) {
            return null;
        }
        PasajeroCrearDTO dto = new PasajeroCrearDTO();
        dto.setId(model.getId());
        dto.setUsuarioId(model.getUsuario().getId());
        dto.setEstado(model.getEstado());
        return dto;
    }

    public Pasajero toEntity(PasajeroCrearDTO dto) {

        if (dto == null) {
            return null;
        }
        Optional<UsuarioDTO> usuario = usuarioServicer.findByIdDto(dto.getUsuarioId());
        if(usuario.isEmpty()){
            return null;
        }
        Usuario usuarioModel = usuarioMapper.toEntity(usuario.get());
        Pasajero model = new Pasajero();
        model.setId(dto.getId());
        model.setEstado(dto.getEstado());
        model.setUsuario(usuarioModel);
        return model;
    }
}
