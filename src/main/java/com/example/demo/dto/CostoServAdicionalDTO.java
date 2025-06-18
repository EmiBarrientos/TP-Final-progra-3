package com.example.demo.dto;

import com.example.demo.model.enums.ServAdicionalEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostoServAdicionalDTO {
    private Long id;
    private ServAdicionalEnum nombre;
    private Double precio;

}
