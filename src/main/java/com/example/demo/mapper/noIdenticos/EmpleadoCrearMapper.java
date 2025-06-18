package com.example.demo.mapper.noIdenticos;

<<<<<<< HEAD
import com.example.demo.auth.dto.UsuarioDTO;
import com.example.demo.dto.crear.EmpleadoCrearDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.model.Empleado;


import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.service.UsuarioService;
=======
import com.example.demo.dto.crear.EmpleadoCrearDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.model.Empleado;
import com.example.demo.auth.entity.Usuario;
import com.example.demo.model.embeddable.Direccion;
import com.example.demo.auth.enums.Rol;
import com.example.demo.auth.servicios.UsuarioService;
>>>>>>> da2898a8d0c8341af32c290337d8291892917938
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        dto.setEstado(model.getEstado());

        // todos los demas de usuario
        dto.setId(model.getId());
        dto.setDni(model.getUsuario().getDni());
        dto.setNombre(model.getUsuario().getNombre());
        dto.setApellido(model.getUsuario().getApellido());
        dto.setTelefono(model.getUsuario().getTelefono());
        dto.setEmail(model.getUsuario().getEmail());

        // direccion
        dto.setCalle(model.getUsuario().getDireccion().getCalle());
        dto.setNumero(model.getUsuario().getDireccion().getNumero());
        dto.setCiudad(model.getUsuario().getDireccion().getCiudad());
        dto.setProvincia(model.getUsuario().getDireccion().getProvincia());
        dto.setCodigoPostal(model.getUsuario().getDireccion().getCodigoPostal());
        dto.setPais(model.getUsuario().getDireccion().getPais());

        dto.setUsername(model.getUsuario().getUsername());
        dto.setPassword(model.getUsuario().getPassword());

        dto.setRol(String.valueOf(model.getUsuario().getRol()));



        return dto;
    }

    public Empleado toEntity(EmpleadoCrearDTO dto) {

        if (dto == null) {
            return null;
        }
<<<<<<< HEAD
        Optional<UsuarioDTO> usuario = usuarioServicer.findByIdDto(dto.getUsuarioId());
        if(usuario.isEmpty()){
            return null;
        }
        Usuario usuarioModel = usuarioMapper.toEntity(usuario.get());
=======


>>>>>>> da2898a8d0c8341af32c290337d8291892917938
        Empleado model = new Empleado();
        model.setId(dto.getId());
        model.setEstado(dto.getEstado());

        Usuario modelU = new Usuario();

        modelU.setId(dto.getIdUsuario());
        modelU.setDni(dto.getDni());
        modelU.setNombre(dto.getNombre());
        modelU.setApellido(dto.getApellido());
        modelU.setTelefono(dto.getTelefono());
        modelU.setEmail(dto.getEmail());

        // direccion
        Direccion direccion = new Direccion();
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setCiudad(dto.getCiudad());
        direccion.setProvincia(dto.getProvincia());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setPais(dto.getPais());
        modelU.setDireccion(direccion);

        modelU.setUsername(dto.getUsername());
        modelU.setPassword(dto.getPassword());

        modelU.setRol(Rol.valueOf(dto.getRol()));


        model.setUsuario(modelU);

        return model;
    }
}
