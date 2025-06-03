package com.example.demo.repository;

import com.example.demo.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    List<Habitacion> findByEstado(String estado);

    List<Habitacion> findByTipoHabitacion(String tipo);

    Habitacion findByNumeroHabitacion(String numero);

    // metodo nuevo
    @Query("SELECT h FROM Habitacion h WHERE h.id NOT IN :ids")
    List<Habitacion> findHabitacionesNoReservadas(@Param("ids") List<Long> ids);

}