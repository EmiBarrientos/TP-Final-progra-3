package com.example.demo.repository;

import com.example.demo.model.Credenciales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredencialesRepository extends JpaRepository<Credenciales, Long> {
    Credenciales findByNombreUsuario(String nombreUsuario);
}