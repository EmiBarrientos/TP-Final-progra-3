package com.example.demo.mapper.noIdenticos;

import com.example.demo.dto.crear.PasajeroConUsuarioCreadoCrearDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.model.Pasajero;
import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.repository.UsuarioRepository;
import com.example.demo.auth.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PasajeroConUsuarioCreadoCrearMapper {

    private final UsuarioRepository repositoryUsuario;
    private final UsuarioService usuarioServicer;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public PasajeroConUsuarioCreadoCrearMapper(UsuarioService usuarioServicer,
                                               UsuarioMapper usuarioMapper,
                                               UsuarioRepository repositoryUsuario) {
        this.usuarioServicer = usuarioServicer;
        this.usuarioMapper = usuarioMapper;
        this.repositoryUsuario = repositoryUsuario;
    }


    public PasajeroConUsuarioCreadoCrearDTO toDto(Pasajero model) {
        if (model == null) {
            return null;
        }
        PasajeroConUsuarioCreadoCrearDTO dto = new PasajeroConUsuarioCreadoCrearDTO();
        dto.setId(model.getId());
        dto.setUsuarioId(model.getUsuario().getId());
        dto.setEstado(model.getEstado());
        return dto;
    }

    public Pasajero toEntity(PasajeroConUsuarioCreadoCrearDTO dto) {

        if (dto == null) {
            return null;
        }

        Optional<Usuario> modelUOptional = repositoryUsuario.findById(dto.getUsuarioId());
        if(modelUOptional.isPresent()) {

            Usuario modelU = modelUOptional.get();
            Pasajero model = new Pasajero();
            model.setId(dto.getId());
            model.setEstado(dto.getEstado());
            model.setUsuario(modelU);

            return model;
        }
        return null;
    }
}
