package com.example.demo.auth.repository;

import com.example.demo.auth.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByDni(String dni);
    Usuario findByEmail(String email);
    Optional<Usuario> findByUsername(String username);
}