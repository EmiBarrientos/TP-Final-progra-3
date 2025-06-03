package com.example.demo.repository;

import com.dream_team.proyecto_final_progra3.entity.ServAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServAdicionalRepository extends JpaRepository<ServAdicional, Long> {
    Optional <ServAdicional> findByNombreIgnoreCase(String nombre);

}
