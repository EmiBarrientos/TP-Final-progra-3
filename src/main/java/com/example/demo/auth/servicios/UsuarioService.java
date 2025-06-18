package com.example.demo.auth.servicios;

import com.example.demo.auth.dto.UsuarioDTO;
import com.example.demo.dto.crear.UsuarioCrearDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.mapper.noIdenticos.UsuarioCrearMapper;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioCrearMapper usuarioCrearMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, UsuarioCrearMapper usuarioCrearMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.usuarioCrearMapper = usuarioCrearMapper;
    }

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> findById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDto);
    }

    public Optional<UsuarioDTO> save(UsuarioCrearDTO usuarioCrearDto) {

        Usuario usuario = usuarioCrearMapper.toEntity(usuarioCrearDto);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return Optional.of(usuarioMapper.toDto(savedUsuario));
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<UsuarioDTO> findByDni(String dni) {
        return Optional.ofNullable(usuarioRepository.findByDni(dni))
                .map(usuarioMapper::toDto);
    }

    public Optional<UsuarioDTO> findByEmail(String email) {
        return Optional.ofNullable(usuarioRepository.findByEmail(email))
                .map(usuarioMapper::toDto);
    }

    public Optional<UsuarioDTO> updateUsuario(Long id, UsuarioCrearDTO dtoCrearDetails) {
        dtoCrearDetails.setId(id); // no puede modificar el id
        Usuario modelCrearDetails = usuarioCrearMapper.toEntity(dtoCrearDetails); //creamos el modelo con los cambios y el resto null
        Optional<Usuario> model = usuarioRepository.findById(id); //buscamos el model a cambiar
        if (model.isPresent()) {
            Usuario updatedModel = model.get(); // el model a cambiar
            ReflectionMapper.actualizarCamposNoNulos(modelCrearDetails,updatedModel); // actualizamos el model
            usuarioRepository.save(updatedModel);
            Optional<UsuarioDTO> respuesta = Optional.ofNullable(usuarioMapper.toDto(updatedModel));
            return respuesta;
        } else {
            return Optional.ofNullable(null);
        }
    }


}