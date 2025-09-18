package com.example.demo.dto.crear;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionCrearDTO {
    private Long id;
    private String numeroHabitacion;
    private Integer capacidad;
    private String tipoHabitacion;
    private String estado;
    // Campos booleanos para cada servicio
    private boolean wifi;
    private boolean tvCable;
    private boolean aireAcondicionado;
    private boolean desayuno;
    private boolean cajaFuerte;
    private boolean pileta;
    private boolean hidromasaje;
}


