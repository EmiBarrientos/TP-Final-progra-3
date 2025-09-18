package com.example.demo.repository;

import com.example.demo.model.CostoHabitacion;
import com.example.demo.model.enums.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostoHabitacionRepository extends JpaRepository<CostoHabitacion, Long> {
    CostoHabitacion findByTipoHabitacion(TipoHabitacion tipo);
}