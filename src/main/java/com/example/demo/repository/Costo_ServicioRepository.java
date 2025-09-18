package com.example.demo.repository;

import com.example.demo.model.Costo_Servicio;
import com.example.demo.model.enums.ServicioEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Costo_ServicioRepository extends JpaRepository<Costo_Servicio, Long> {
    Costo_Servicio findByNombre(ServicioEnum nombre);
}