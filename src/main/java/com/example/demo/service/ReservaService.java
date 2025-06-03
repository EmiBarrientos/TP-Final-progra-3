package com.example.demo.service;

import com.example.demo.dto.crear.ReservaCrearDTO;
import com.example.demo.dto.ReservaDTO;
import com.example.demo.mapper.ReservaMapper;
import com.example.demo.mapper.noIdenticos.ReservaCrearMapper;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.*;
import com.example.demo.model.enums.EstadoReserva;
import com.example.demo.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final ReservaCrearMapper reservaCrearMapper;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ReservaMapper reservaMapper, ReservaCrearMapper reservaCrearMapper) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
        this.reservaCrearMapper = reservaCrearMapper;
    }



    public List<ReservaDTO> findAll() {
        return reservaRepository.findAll()
                .stream().map(r->reservaMapper.toDto(r))
                .toList();
    }

    public Optional<ReservaDTO> findById(Long id) {

        Reserva reserva = reservaRepository.findById(id).get();
        ReservaDTO reservaDTO = reservaMapper.toDto(reserva);
        return Optional.ofNullable(reservaDTO);
    }

    public Optional <ReservaDTO> save(ReservaCrearDTO reservaCrearDTO) {
        Reserva reserva = reservaCrearMapper.toEntity(reservaCrearDTO);
        return Optional.ofNullable(
                reservaMapper.toDto(reservaRepository.save(reserva))
        );
    }

    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<ReservaDTO> findByFechaInicioBetween(LocalDate inicio, LocalDate fin) {
        return reservaRepository.findByFechaInicioBetween(inicio, fin)
                .stream().map(r->reservaMapper.toDto(r))
                .toList();
    }

    public List<ReservaDTO> findByPasajeroId(Long pasajeroId) {
        return reservaRepository.findByPasajeroId(pasajeroId)
                .stream().map(r->reservaMapper.toDto(r))
                .toList();
    }

    public List<ReservaDTO> findByHabitacionId(Long habitacionId) {
        return reservaRepository.findByHabitacionId(habitacionId)
                .stream().map(r->reservaMapper.toDto(r))
                .toList();
    }

    public List<Long> obtenerHabitacionesReservadas(LocalDate fechaInicio, LocalDate fechaFin) { // me devuelve las id de las habitaciones reservadas entre esas fechas (independientemente del estado
        return reservaRepository.findHabitacionIdsReservadasEntreFechas(fechaInicio, fechaFin);
    }

    public List<ReservaDTO> findByEstado(EstadoReserva estado) {
        return reservaRepository.findByEstado(estado).stream()
                .map(r->reservaMapper.toDto(r)).toList();
    }

    public Optional<ReservaDTO> updateReserva(Long id, ReservaCrearDTO dtoCrearDetails) {
        dtoCrearDetails.setId(id); // no puede modificar el id
        Reserva modelCrearDetails = reservaCrearMapper.toEntity(dtoCrearDetails); //creamos el modelo con los cambios y el resto null
        Optional<Reserva> model = reservaRepository.findById(id); //buscamos el model a cambiar
        if (model.isPresent()) {
            Reserva updatedModel = model.get(); // el model a cambiar
            ReflectionMapper.actualizarCamposNoNulos(modelCrearDetails,updatedModel); // actualizamos el model
            reservaRepository.save(updatedModel);
            Optional<ReservaDTO> respuesta = Optional.ofNullable(reservaMapper.toDto(updatedModel));
            return respuesta;
        } else {
            return Optional.ofNullable(null);
        }
    }
}