package com.example.demo.repository;


import com.example.demo.model.CostoServAdicional;
import com.example.demo.model.ServAdicional;
import com.example.demo.model.enums.ServAdicionalEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CostoServAdicionalRepository extends JpaRepository <CostoServAdicional, Long>{
    /*Optional <CostoServAdicional>  findByServAdicionalAndFechaDesdeLessThanEqualAndFechaHastaGreaterThanEqual(
            ServAdicional servAdicional, LocalDate fechaDesde, LocalDate fechaHasta
    );*/
    Optional<CostoServAdicional> findByNombre(ServAdicionalEnum nombre);
}
