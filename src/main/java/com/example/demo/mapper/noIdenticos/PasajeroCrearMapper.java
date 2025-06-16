package com.example.demo.mapper.noIdenticos;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.crear.PasajeroCrearDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.model.Pasajero;
import com.example.demo.model.Usuario;
import com.example.demo.model.embeddable.Direccion;
import com.example.demo.service.UsuarioService;
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
        //dto.setUsuarioId(model.getUsuario().getId());
        dto.setId(model.getId());
        dto.setDni(model.getUsuario().getDni());
        dto.setNombre(model.getUsuario().getNombre());
        dto.setApellido(model.getUsuario().getApellido());
        dto.setTelefono(model.getUsuario().getTelefono());
        dto.setEmail(model.getUsuario().getEmail());
        dto.setPermisos(model.getUsuario().getPermisos()); // administrador, empleado, pasajero
        //private Direccion direccion;
        dto.setCalle(model.getUsuario().getDireccion().getCalle());
        dto.setNumero(model.getUsuario().getDireccion().getNumero());
        dto.setCiudad(model.getUsuario().getDireccion().getCiudad());
        dto.setProvincia(model.getUsuario().getDireccion().getProvincia());
        dto.setCodigoPostal(model.getUsuario().getDireccion().getCodigoPostal());
        dto.setPais(model.getUsuario().getDireccion().getPais());



        dto.setEstado(model.getEstado());
        return dto;
    }

    public Pasajero toEntity(PasajeroCrearDTO dto) {

        if (dto == null) {
            return null;
        }

        Usuario modelU = new Usuario();
        modelU.setId(dto.getId());
        modelU.setDni(dto.getDni());
        modelU.setNombre(dto.getNombre());
        modelU.setApellido(dto.getApellido());
        modelU.setTelefono(dto.getTelefono());
        modelU.setEmail(dto.getEmail());
        modelU.setPermisos(dto.getPermisos()); // administrador, empleado, pasajero
        //private Direccion direccion;
        Direccion direccion = new Direccion();
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setCiudad(dto.getCiudad());
        direccion.setProvincia(dto.getProvincia());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setPais(dto.getPais());
        modelU.setDireccion(direccion);

        Pasajero model = new Pasajero();
        model.setId(dto.getId());
        model.setEstado(dto.getEstado());
        model.setUsuario(modelU);
        return model;
    }
}
