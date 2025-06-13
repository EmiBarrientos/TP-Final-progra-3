package com.example.demo.mapper;

import com.example.demo.dto.HabitacionDTO;
import com.example.demo.model.Habitacion;
import com.example.demo.model.enums.EstadoHabitacion;
import com.example.demo.model.enums.ServicioEnum;
import com.example.demo.model.enums.TipoHabitacion;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class HabitacionMapper {

    public HabitacionDTO toDto(Habitacion habitacion) {
        if (habitacion == null) {
            return null;
        }
        HabitacionDTO dto = new HabitacionDTO();
        dto.setId(habitacion.getId());
        dto.setNumeroHabitacion(habitacion.getNumeroHabitacion());
        dto.setCapacidad(habitacion.getCapacidad());
        dto.setTipoHabitacion(habitacion.getTipoHabitacion() != null ? habitacion.getTipoHabitacion().name() : null);
        dto.setEstado(habitacion.getEstado() != null ? habitacion.getEstado().name() : null);
        dto.setReservas(habitacion.getReservas());

        if (habitacion.getServicios() != null) {
            Map<String, Boolean> serviciosMap = habitacion.getServicios().entrySet().stream()
                    .collect(Collectors.toMap(

                            entry -> entry.getKey().name(),
                            Map.Entry::getValue
                    ));
            dto.setServicios(serviciosMap);
        }

        return dto;
    }

    public Habitacion toEntity(HabitacionDTO dto) {
        if (dto == null) {
            return null;
        }

        Habitacion habitacion = new Habitacion();
        habitacion.setId(dto.getId());
        habitacion.setNumeroHabitacion(dto.getNumeroHabitacion());
        habitacion.setCapacidad(dto.getCapacidad());

        if (dto.getTipoHabitacion() != null) {
            habitacion.setTipoHabitacion(TipoHabitacion.valueOf(dto.getTipoHabitacion()));
        }

        if (dto.getEstado() != null) {
            habitacion.setEstado(EstadoHabitacion.valueOf(dto.getEstado()));
        }

        habitacion.setReservas(dto.getReservas());

        if (dto.getServicios() != null) {
            Map<ServicioEnum, Boolean> serviciosMap = dto.getServicios().entrySet().stream()
                    .collect(Collectors.toMap(
                            entry -> ServicioEnum.valueOf(entry.getKey()),
                            Map.Entry::getValue
                    ));
            habitacion.setServicios(serviciosMap);
        }

        return habitacion;
    }
}

