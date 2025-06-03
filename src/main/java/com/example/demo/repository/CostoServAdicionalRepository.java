package com.example.demo.repository;

import com.dream_team.proyecto_final_progra3.entity.CostoServAdicional;
import com.dream_team.proyecto_final_progra3.entity.enums.ServAdicionalEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostoServAdicionalRepository extends JpaRepository<CostoServAdicional, Long> {
    Optional<CostoServAdicional> findByNombre(ServAdicionalEnum nombre);
}