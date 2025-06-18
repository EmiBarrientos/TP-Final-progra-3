package com.example.demo.mapper.noIdenticos;

import com.example.demo.dto.crear.EmpleadoConUsuarioCreadoCrearDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.model.Empleado;
import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.repository.UsuarioRepository;
import com.example.demo.auth.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmpleadoConUsuarioCreadoCrearMapper {

    private final UsuarioRepository repositoryUsuario;
    private final UsuarioService usuarioServicer;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public EmpleadoConUsuarioCreadoCrearMapper(UsuarioService usuarioServicer,
                                               UsuarioMapper usuarioMapper,
                                               UsuarioRepository repositoryUsuario) {
        this.usuarioServicer = usuarioServicer;
        this.usuarioMapper = usuarioMapper;
        this.repositoryUsuario = repositoryUsuario;
    }


    public EmpleadoConUsuarioCreadoCrearDTO toDto(Empleado model) {
        if (model == null) {
            return null;
        }
        EmpleadoConUsuarioCreadoCrearDTO dto = new EmpleadoConUsuarioCreadoCrearDTO();
        dto.setId(model.getId());
        dto.setUsuarioId(model.getUsuario().getId());
        dto.setEstado(model.getEstado());
        dto.setHorasTrabajadas(model.getHorasTrabajadas());
        return dto;
    }

    public Empleado toEntity(EmpleadoConUsuarioCreadoCrearDTO dto) {

        if (dto == null) {
            return null;
        }

        Optional<Usuario> modelUOptional = repositoryUsuario.findById(dto.getUsuarioId());
        if(modelUOptional.isPresent()) {

            Usuario modelU = modelUOptional.get();
            Empleado model = new Empleado();
            model.setId(dto.getId());
            model.setEstado(dto.getEstado());
            model.setHorasTrabajadas(dto.getHorasTrabajadas());
            model.setUsuario(modelU);

            return model;
        }
        return null;
    }
}
