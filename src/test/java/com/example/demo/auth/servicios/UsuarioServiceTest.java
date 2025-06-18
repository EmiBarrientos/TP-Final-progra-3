package com.example.demo.auth.servicios;

import com.example.demo.auth.dto.UsuarioDTO;
import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.repository.UsuarioRepository;
import com.example.demo.dto.crear.UsuarioCrearDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.mapper.noIdenticos.UsuarioCrearMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private UsuarioCrearMapper usuarioCrearMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    public UsuarioServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateUsuario_whenUsuarioExists_shouldUpdateAndReturnUsuarioDTO() {
        Long id = 1L;

        UsuarioCrearDTO usuarioCrearDTO = new UsuarioCrearDTO();
        usuarioCrearDTO.setId(id);

        Usuario existingUsuario = new Usuario();
        existingUsuario.setId(id);

        Usuario mappedUsuario = new Usuario();
        mappedUsuario.setId(id);

        Usuario updatedUsuario = new Usuario();
        updatedUsuario.setId(id);

        UsuarioDTO mappedUsuarioDTO = new UsuarioDTO();

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(existingUsuario));
        when(usuarioCrearMapper.toEntity(usuarioCrearDTO)).thenReturn(mappedUsuario);
        when(usuarioMapper.toDto(updatedUsuario)).thenReturn(mappedUsuarioDTO);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(updatedUsuario);

        Optional<UsuarioDTO> result = usuarioService.updateUsuario(id, usuarioCrearDTO);

        assertTrue(result.isPresent());
        assertEquals(mappedUsuarioDTO, result.get());
        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void updateUsuario_whenUsuarioDoesNotExist_shouldReturnEmptyOptional() {
        Long id = 1L;

        UsuarioCrearDTO usuarioCrearDTO = new UsuarioCrearDTO();
        usuarioCrearDTO.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        Optional<UsuarioDTO> result = usuarioService.updateUsuario(id, usuarioCrearDTO);

        assertFalse(result.isPresent());
        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(0)).save(any(Usuario.class));
    }


    /*@Test
    void findById_whenUsuarioExists_shouldReturnUsuarioDTO() {
        Long id = 1L;

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setUsername("gabi");

        UsuarioCrearDTO usuarioDTO = new UsuarioCrearDTO();
        usuarioDTO.setId(id);
        usuarioDTO.setUsername("gabi");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDto(usuario)).thenReturn(usuarioDTO);

        Optional<UsuarioDTO> result = usuarioService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(usuarioDTO, result.get());
        verify(usuarioRepository).findById(id);
        verify(usuarioMapper).toDto(usuario);
    }*/

    @Test
    void findById_whenUsuarioNotExists_shouldReturnEmpty() {
        Long id = 99L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        Optional<UsuarioDTO> result = usuarioService.findById(id);

        assertFalse(result.isPresent());
        verify(usuarioRepository).findById(id);
        verify(usuarioMapper, never()).toDto(any());
    }
}