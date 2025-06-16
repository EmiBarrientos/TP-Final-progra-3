package com.example.demo.repository;

import com.example.demo.model.ServAdicional;
import com.example.demo.model.enums.ServAdicionalEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServAdicionalRepository extends JpaRepository<ServAdicional, Long> {
    Optional<ServAdicional> findByNombre(ServAdicionalEnum nombre);
}
