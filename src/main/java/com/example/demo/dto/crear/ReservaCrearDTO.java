package com.example.demo.dto.crear;

import com.example.demo.model.enums.EstadoReserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaCrearDTO {
    private Long id;
    private Long pasajeroId;
    private Long habitacionId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoReserva estado;
    private String observaciones;
    // private Map<EstadoReserva,EmpleadoAccionReserva> estadoReservaEmpleadoAccionReservaMap;
    // Se hace con el controller de createEmpleadoAccionReservaMap
        // (nuevoEstadoReserva, idReserva, idEmpleado)
        // private EstadoReserva estadoReserva;
        //private EmpleadoAccionReserva empleadoAccionReserva

    // listado de inter de servicios adicionales
    private Integer masajes; // MASAJES,
    private Integer spa;
    private Integer  pileta; //PILETA,
    private Integer  cena; //CENA,
    private Integer  almuerzo; // ALMUERZO,
    private Integer desayuno; // DESAYUNO,
    private Integer facial; // FACIAL,
    private Integer manicura; // MANICURA,
    private Integer lavanderia; // LAVANDERIA



    private int cantidadPasajeros; // solo en el dto crear
}