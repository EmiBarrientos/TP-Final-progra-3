package com.example.demo.repository;


import com.example.demo.model.EmpleadoAccionReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpleadoAccionReservaRepository extends JpaRepository<EmpleadoAccionReserva, Long> {

}
