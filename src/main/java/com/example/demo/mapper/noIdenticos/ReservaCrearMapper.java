package com.example.demo.mapper.noIdenticos;

import com.example.demo.dto.crear.ReservaCrearDTO;
import com.example.demo.mapper.HabitacionMapper;
import com.example.demo.mapper.PasajeroMapper;
import com.example.demo.model.*;
import com.example.demo.model.enums.ServAdicionalEnum;
import com.example.demo.repository.CostoServAdicionalRepository;
import com.example.demo.repository.HabitacionRepository;
import com.example.demo.repository.PasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ReservaCrearMapper {

    private final CostoServAdicionalRepository costoServAdicionalRepository;
    private final PasajeroRepository pasajeroRepository;
    private final PasajeroMapper pasajeroMapper;
    private final HabitacionRepository habitacionRepository;
    private final HabitacionMapper habitacionMapper;

    @Autowired
    public ReservaCrearMapper(CostoServAdicionalRepository costoServAdicionalRepository, PasajeroRepository pasajeroRepository, PasajeroMapper pasajeroMapper, HabitacionRepository habitacionRepository, HabitacionMapper habitacionMapper) {
        this.costoServAdicionalRepository = costoServAdicionalRepository;
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

        Map<CostoServAdicional, Integer> serviciosAdicionalesConCantidad = new HashMap<>();


        serviciosAdicionalesConCantidad.put(
                costoServAdicionalRepository.findByNombre(ServAdicionalEnum.MASAJES)
                        .orElseThrow(() -> new RuntimeException("MASAJES no disponible")),
                dto.getMasajes());

        serviciosAdicionalesConCantidad.put(
                costoServAdicionalRepository.findByNombre(ServAdicionalEnum.SPA)
                        .orElseThrow(() -> new RuntimeException("SPA no disponible")),
                dto.getSpa());

        serviciosAdicionalesConCantidad.put(
                costoServAdicionalRepository.findByNombre(ServAdicionalEnum.PILETA)
                        .orElseThrow(() -> new RuntimeException("PILETA no disponible")),
                dto.getPileta());

        serviciosAdicionalesConCantidad.put(
                costoServAdicionalRepository.findByNombre(ServAdicionalEnum.CENA)
                        .orElseThrow(() -> new RuntimeException("CENA no disponible")),
                dto.getCena());

        serviciosAdicionalesConCantidad.put(
                costoServAdicionalRepository.findByNombre(ServAdicionalEnum.ALMUERZO)
                        .orElseThrow(() -> new RuntimeException("ALMUERZO no disponible")),
                dto.getAlmuerzo());

        serviciosAdicionalesConCantidad.put(
                costoServAdicionalRepository.findByNombre(ServAdicionalEnum.DESAYUNO)
                        .orElseThrow(() -> new RuntimeException("DESAYUNO no disponible")),
                dto.getDesayuno());

        serviciosAdicionalesConCantidad.put(
                costoServAdicionalRepository.findByNombre(ServAdicionalEnum.FACIAL)
                        .orElseThrow(() -> new RuntimeException("FACIAL no disponible")),
                dto.getFacial());

        serviciosAdicionalesConCantidad.put(
                costoServAdicionalRepository.findByNombre(ServAdicionalEnum.MANICURA)
                        .orElseThrow(() -> new RuntimeException("MANICURA no disponible")),
                dto.getManicura());

        serviciosAdicionalesConCantidad.put(
                costoServAdicionalRepository.findByNombre(ServAdicionalEnum.LAVANDERIA)
                        .orElseThrow(() -> new RuntimeException("LAVANDERIA no disponible")),
                dto.getLavanderia());

        model.setServiciosAdicionalesConCantidad(serviciosAdicionalesConCantidad);



        return model;
    }

    public Optional<Integer> toCantidadPasajeros (ReservaCrearDTO dto){
        if (dto == null) {
            return Optional.empty();
        }
        if(dto.getCantidadPasajeros()<0){
            return Optional.empty();
        }
        return Optional.of(dto.getCantidadPasajeros());
    }
}
