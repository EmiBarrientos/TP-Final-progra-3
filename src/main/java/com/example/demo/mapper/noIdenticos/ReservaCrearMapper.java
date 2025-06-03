package com.example.demo.mapper.noIdenticos;

import com.example.demo.dto.crear.ReservaCrearDTO;
import com.example.demo.mapper.HabitacionMapper;
import com.example.demo.mapper.PasajeroMapper;
import com.example.demo.model.*;
import com.example.demo.repository.HabitacionRepository;
import com.example.demo.repository.PasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReservaCrearMapper {

    private final PasajeroRepository pasajeroRepository;
    private final PasajeroMapper pasajeroMapper;
    private final HabitacionRepository habitacionRepository;
    private final HabitacionMapper habitacionMapper;

    @Autowired
    public ReservaCrearMapper(PasajeroRepository pasajeroRepository, PasajeroMapper pasajeroMapper,
                              HabitacionRepository habitacionRepository, HabitacionMapper habitacionMapper) {
        this.pasajeroRepository = pasajeroRepository;
        this.pasajeroMapper = pasajeroMapper;
        this.habitacionRepository = habitacionRepository;
        this.habitacionMapper = habitacionMapper;
    }

    public ReservaCrearDTO toDto(Reserva model) {
        if (model == null) {
            return null;
        }
        ReservaCrearDTO dto = new ReservaCrearDTO();
        dto.setId(model.getId());
        dto.setFechaInicio(model.getFechaInicio());
        dto.setFechaFin(model.getFechaFin());
        dto.setHabitacionId(model.getHabitacion().getId());
        dto.setPasajeroId(model.getPasajero().getId());
        dto.setEstado(model.getEstado());
        dto.setObservaciones(model.getObservaciones());
        return dto;
    }

    public Reserva toEntity(ReservaCrearDTO dto) {

        if (dto == null) {
            return null;
        }
        Optional<Pasajero> pasajero = pasajeroRepository.findById(dto.getPasajeroId());
        Optional<Habitacion> habitacion = habitacionRepository.findById(dto.getHabitacionId());
        if(pasajero.isEmpty() || habitacion.isEmpty()){
            return null;
        }
        Pasajero pasajeroModel = pasajero.get();
        Reserva model = new Reserva();
        model.setId(dto.getId());
        model.setEstado(dto.getEstado());
        model.setFechaFin(dto.getFechaFin());
        model.setFechaInicio(dto.getFechaInicio());
        model.setHabitacion(habitacion.get());
        model.setPasajero(pasajeroModel);
        model.setObservaciones(dto.getObservaciones());
        return model;
    }
}
