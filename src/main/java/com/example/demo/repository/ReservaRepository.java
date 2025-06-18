package com.example.demo.repository;

import com.example.demo.model.Reserva;
import com.example.demo.model.enums.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByFechaInicioBetween(LocalDate inicio, LocalDate fin);
    List<Reserva> findByPasajeroId(Long pasajeroId);
    List<Reserva> findByHabitacionId(Long habitacionId);

    List<Reserva> findByEstado(EstadoReserva estado);

    // metodo nuevo
    @Query("SELECT r.habitacion.id FROM Reserva r " +
            "WHERE r.fechaInicio <= :fechaFin AND r.fechaFin >= :fechaInicio")
    List<Long> findHabitacionIdsReservadasEntreFechas(@Param("fechaInicio") LocalDate fechaInicio,
                                                      @Param("fechaFin") LocalDate fechaFin);

}