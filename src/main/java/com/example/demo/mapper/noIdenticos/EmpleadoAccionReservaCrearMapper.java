package com.example.demo.mapper.noIdenticos;


import com.example.demo.dto.crear.EmpleadoAccionReservaCrearDTO;
import com.example.demo.mapper.EmpleadoMapper;
import com.example.demo.mapper.ReservaMapper;
import com.example.demo.model.Empleado;
import com.example.demo.model.EmpleadoAccionReserva;
import com.example.demo.model.Reserva;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmpleadoAccionReservaCrearMapper {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;
    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;

    @Autowired
    public EmpleadoAccionReservaCrearMapper(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper, ReservaRepository reservaRepository, ReservaMapper reservaMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
    }

    public EmpleadoAccionReservaCrearDTO toDto(EmpleadoAccionReserva model) {
        if (model == null) {
            return null;
        }
        EmpleadoAccionReservaCrearDTO dto = new EmpleadoAccionReservaCrearDTO();
        dto.setId(model.getId());
        dto.setEstado(model.getEstado());
        dto.setIdEmpleado(model.getEmpleado().getId());
        dto.setIdReserva(model.getReserva().getId());
        return dto;
    }

    public EmpleadoAccionReserva toEntity(EmpleadoAccionReservaCrearDTO dto) {
        if (dto == null) {
            return null;
        }
        Optional<Reserva> reserva = reservaRepository.findById(dto.getIdReserva());
        Optional<Empleado> empleado = empleadoRepository.findById(dto.getIdEmpleado());

        EmpleadoAccionReserva model = new EmpleadoAccionReserva();
        model.setEmpleado((empleado.get()));
        model.setReserva(reserva.get());
        model.setEstado(dto.getEstado());
        model.setId(dto.getId());
        return model;
    }
}

