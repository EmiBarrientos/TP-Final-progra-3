package com.example.demo.mapper.noIdenticos;

import com.example.demo.dto.crear.HabitacionCrearDTO;
import com.example.demo.model.Habitacion;
import com.example.demo.model.enums.EstadoHabitacion;
import com.example.demo.model.enums.ServicioEnum;
import com.example.demo.model.enums.TipoHabitacion;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HabitacionCrearMapper {

    public HabitacionCrearDTO toDto(Habitacion habitacion) {
        if (habitacion == null) {
            return null;
        }
        HabitacionCrearDTO dto = new HabitacionCrearDTO();
        dto.setId(habitacion.getId());
        dto.setNumeroHabitacion(habitacion.getNumeroHabitacion());
        dto.setCapacidad(habitacion.getCapacidad());
        dto.setTipoHabitacion(habitacion.getTipoHabitacion() != null ? habitacion.getTipoHabitacion().name() : null);
        dto.setEstado(habitacion.getEstado() != null ? habitacion.getEstado().name() : null);

        Map<ServicioEnum,Boolean> servicios = habitacion.getServicios(); // ver si hay más
        dto.setWifi(servicios.get(ServicioEnum.WIFI));
        dto.setTvCable(servicios.get(ServicioEnum.TV_CABLE));
        dto.setAireAcondicionado(servicios.get(ServicioEnum.AIRE_ACONDICIONADO));
        dto.setDesayuno(servicios.get(ServicioEnum.DESAYUNO));
        dto.setCajaFuerte(servicios.get(ServicioEnum.CAJA_FUERTE));
        dto.setPileta(servicios.get(ServicioEnum.PILETA));
        dto.setHidromasaje(servicios.get(ServicioEnum.HIDROMASAJE));

        return dto;
    }

    public Habitacion toEntity(HabitacionCrearDTO dto) {
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

        Map<ServicioEnum,Boolean> servicios = new HashMap<>();
        servicios.put(ServicioEnum.WIFI, dto.isWifi());
        servicios.put(ServicioEnum.TV_CABLE, dto.isTvCable());
        servicios.put(ServicioEnum.AIRE_ACONDICIONADO, dto.isAireAcondicionado());
        servicios.put(ServicioEnum.DESAYUNO, dto.isDesayuno());
        servicios.put(ServicioEnum.CAJA_FUERTE, dto.isCajaFuerte());
        servicios.put(ServicioEnum.PILETA, dto.isPileta());
        servicios.put(ServicioEnum.HIDROMASAJE, dto.isHidromasaje());
        habitacion.setServicios(servicios);

        return habitacion;
    }
}

