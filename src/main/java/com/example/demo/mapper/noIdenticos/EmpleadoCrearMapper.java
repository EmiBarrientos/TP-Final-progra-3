package com.example.demo.mapper.noIdenticos;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.crear.EmpleadoCrearDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.model.Empleado;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmpleadoCrearMapper {

    private final UsuarioService usuarioServicer;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public EmpleadoCrearMapper(UsuarioService usuarioServicer, UsuarioMapper usuarioMapper) {
        this.usuarioServicer = usuarioServicer;
        this.usuarioMapper = usuarioMapper;
    }


    public EmpleadoCrearDTO toDto(Empleado model) {
        if (model == null) {
            return null;
        }
        EmpleadoCrearDTO dto = new EmpleadoCrearDTO();
        dto.setId(model.getId());
        dto.setUsuarioId(model.getUsuario().getId());
        dto.setEstado(model.getEstado());
        return dto;
    }

    public Empleado toEntity(EmpleadoCrearDTO dto) {

        if (dto == null) {
            return null;
        }
        Optional<UsuarioDTO> usuario = usuarioServicer.findById(dto.getUsuarioId());
        if(usuario.isEmpty()){
            return null;
        }
        Usuario usuarioModel = usuarioMapper.toEntity(usuario.get());
        Empleado model = new Empleado();
        model.setId(dto.getId());
        model.setEstado(dto.getEstado());
        model.setUsuario(usuarioModel);

        return model;
    }
}
