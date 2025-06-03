package com.example.demo.repository;

import com.dream_team.proyecto_final_progra3.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaRepository extends JpaRepository <Factura, Long> {

    List<Factura> findByReservaId(Long reservaId);
}
