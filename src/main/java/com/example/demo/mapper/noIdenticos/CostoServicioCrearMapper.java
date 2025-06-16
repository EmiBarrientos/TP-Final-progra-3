package com.example.demo.mapper.noIdenticos;

import com.example.demo.dto.crear.CostoServicioCrearDTO;
import com.example.demo.dto.crear.HabitacionCrearDTO;
import com.example.demo.model.Costo_Servicio;
import com.example.demo.model.Habitacion;
import com.example.demo.model.enums.EstadoHabitacion;
import com.example.demo.model.enums.ServicioEnum;
import com.example.demo.model.enums.TipoHabitacion;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CostoServicioCrearMapper {

    public CostoServicioCrearDTO toDto(Costo_Servicio model) {
        if (model == null) {
            return null;
        }
        CostoServicioCrearDTO dto = new CostoServicioCrearDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setCosto(model.getCosto());
        return dto;
    }

    public Costo_Servicio toEntity(CostoServicioCrearDTO dto) {
        if (dto == null) {
            return null;
        }
        Costo_Servicio model = new Costo_Servicio();
        model.setId(dto.getId());
        model.setCosto(dto.getCosto());
        model.setNombre(dto.getNombre());

        return model;
    }
}

