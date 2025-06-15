package com.example.demo.auth.service;


import com.example.demo.auth.dto.UsuarioDTO;
import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.repository.UsuarioRepository;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.mapper.noIdenticos.UsuarioCrearMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioCrearMapper usuarioCrearMapper;


    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario findByDni(String dni) {
        return usuarioRepository.findByDni(dni);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Optional<UsuarioDTO> findByIdDto(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDto);
    }

}