package com.example.demo.service.util;

import com.example.demo.auth.entity.Usuario;
import com.example.demo.auth.repository.UsuarioRepository;
import com.example.demo.dto.CostoHabitacionDTO;
import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.dto.HabitacionDTO;
import com.example.demo.dto.PasajeroDTO;
import com.example.demo.dto.crear.EmpleadoCrearDTO;
import com.example.demo.dto.crear.PasajeroCrearDTO;
import com.example.demo.mapper.EmpleadoMapper;
import com.example.demo.mapper.HabitacionMapper;
import com.example.demo.mapper.PasajeroMapper;
import com.example.demo.mapper.noIdenticos.EmpleadoCrearMapper;
import com.example.demo.mapper.noIdenticos.PasajeroCrearMapper;
import com.example.demo.model.*;
import com.example.demo.model.enums.EstadoReserva;
import com.example.demo.model.enums.ServicioEnum;
import com.example.demo.model.enums.TipoHabitacion;
import com.example.demo.repository.*;
import com.example.demo.service.CostoHabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Util_Service {
    private final HabitacionRepository habitacionRepository;
    private final ReservaRepository reservaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final PasajeroRepository pasajeroRepository;
    private final Costo_ServicioRepository costoServicioRepository;
    private final UsuarioRepository usuarioServicioRepository;


    private final CostoHabitacionService costoHabitacionService;
    private final HabitacionMapper habitacionMapper;
    private final PasajeroCrearMapper pasajeroCrearMapper;
    private final PasajeroMapper pasajeroMapper;
    private final EmpleadoCrearMapper empleadoCrearMapper;
    private final EmpleadoMapper empleadoMapper;








    public Optional<PasajeroDTO> savePasajeroUsuario(PasajeroCrearDTO pasajeroCrearDTO) {

        Pasajero pasajero = pasajeroCrearMapper.toEntity(pasajeroCrearDTO);
        Usuario usuario = usuarioServicioRepository.save(pasajero.getUsuario());
        pasajero.setUsuario(usuario);
        PasajeroDTO pasajeroDTO = pasajeroMapper.toDto(pasajeroRepository.save(pasajero));
        return Optional.of(pasajeroDTO);
    }


    public Optional<EmpleadoDTO> saveEmpleadoUsuario(EmpleadoCrearDTO empleadoCrearDTO) {

        Empleado empleado = empleadoCrearMapper.toEntity(empleadoCrearDTO);
        Usuario usuario = usuarioServicioRepository.save(empleado.getUsuario());
        empleado.setUsuario(usuario);
        EmpleadoDTO empleadoDTO = empleadoMapper.toDto(empleadoRepository.save(empleado));
        return Optional.of(empleadoDTO);
    }





    public List<HabitacionDTO> obtenerHabitacionesDisponibles(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Long> habitacionesReservadas = reservaRepository
                .findHabitacionIdsReservadasEntreFechas(fechaInicio, fechaFin);
        if (habitacionesReservadas.isEmpty()) {

            return habitacionRepository.findAll()
                    .stream().map(p->habitacionMapper.toDto(p)).toList(); // Todas están disponibles
        }
        return habitacionRepository.findHabitacionesNoReservadas(habitacionesReservadas)
                .stream().map(p->habitacionMapper.toDto(p)).toList();
    }


    public List<HabitacionDTO> obtenerHabitacionesDisponiblesPorFechayTipo(
            LocalDate fechaInicio, LocalDate fechaFin, TipoHabitacion tipo) {
        List<HabitacionDTO> habitacionesDisponibles = obtenerHabitacionesDisponibles(
                fechaInicio, fechaFin).stream()
                .filter(p->TipoHabitacion.valueOf(p.getTipoHabitacion()) == tipo).toList();
        return habitacionesDisponibles;
    }




    public void asignarEmpleadoReserva(long reservaId, long empleadoId, EstadoReserva accionEstado) {
        // Traemos al empleado
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        // Ver si esta la reserva
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        // Crear una nueva relación Empleado  Reserva Accion
        EmpleadoAccionReserva accion = EmpleadoAccionReserva.builder()
                .estado(accionEstado)
                .empleado(empleado)
                .reserva(reserva)
                .build();

        // Actualizar la acción en el mapa
        reserva.getEmpleadoAccionEstado().put(accionEstado, accion);
        // actualizar estado
        reserva.setEstado(accionEstado); // deja siempre el ultimo Aca si queremos podemos agregarle regla que si es tal no puede ir a tal
        // Guardar los cambios
        reservaRepository.save(reserva);
    }

    public void asignarPasajeroAReserva(Long reservaId, Long pasajeroId) {
        Pasajero pasajero = pasajeroRepository.findById(pasajeroId)
                .orElseThrow(() -> new RuntimeException("Pasajero no encontrado"));
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setPasajero(pasajero);
        reservaRepository.save(reserva);
    }


    public Double calcularCostoTotal(String numeroHabitacion) {
        Habitacion habitacion = habitacionRepository.findByNumeroHabitacion(numeroHabitacion);
        if (habitacion == null) {
            throw new IllegalArgumentException("La habitación no puede ser nula");
        }
        // Calcular el costo base de la habitacion
        TipoHabitacion tipo = habitacion.getTipoHabitacion();
        String tipoHabitacion = tipo.name();
        CostoHabitacionDTO costoHabitacion = costoHabitacionService.findByTipoHabitacion(tipoHabitacion).get();
        Double total = costoHabitacion.getCosto();
        // Sumar los costos de los servicios activos
        if (habitacion.getServicios() != null) {
            for (Map.Entry<ServicioEnum, Boolean> entry : habitacion.getServicios().entrySet()) {
                if (entry.getValue()) { // Si el servicio está activo (true)
                    try {
                        Costo_Servicio servicio = costoServicioRepository.findByNombre(entry.getKey());
                        Double costoServicio = servicio.getCosto();
                        total += costoServicio;
                    } catch (Exception e) {
                        System.out.println("no hay servicio con ese nombre");
                    }
                }
            }
        }
        return total;
    }


}


//public List<Reserva> findByEstado(String estado) {
//    return reservaRepository.findByEstado(estado);
//}

    /*
    public void AsignarEmpleadoReserva(long reservaId, long empleadoId, EstadoReserva accionEstado) {
        // Traemos al empleado
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        // Ver si esta la reserva
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        // Crear una nueva relación Empleado  Reserva Accion
        EmpleadoAccionReserva accion = EmpleadoAccionReserva.builder()
                .estado(accionEstado)
                .empleado(empleado)
                .reserva(reserva)
                .build();

        // Actualizar la acción en el mapa
        reserva.getEmpleadoAccionEstado().put(accionEstado, accion);
        // actualizar estado
        reserva.setEstado(accionEstado); // deja siempre el ultimo Aca si queremos podemos agregarle regla que si es tal no puede ir a tal
        // Guardar los cambios
        reservaRepository.save(reserva);
    }

    public void asignarPasajeroAReserva(Long reservaId, Long pasajeroId) {
        Pasajero pasajero = pasajeroRepository.findById(pasajeroId)
                .orElseThrow(() -> new RuntimeException("Pasajero no encontrado"));
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setPasajero(pasajero);
        reservaRepository.save(reserva);
    }
*/
    /*public void crearReserva(Reserva reserva){
        List<Habitacion> habitacionesDisponibles = habitacionRepository.encontrarDisponibilidad(reserva.getFechaInicio(), reserva.getFechaFin());
        //comprobamos si la hab esta disponible
        boolean habitacionDisponible = habitacionesDisponibles.stream()
                .anyMatch(habitacion -> habitacion.getId().equals(reserva.getHabitacion().getId()));
        if (!habitacionDisponible) {
            throw new IllegalArgumentException("La habitación con ID " + reserva.getHabitacion().getId() + " no está disponible en las fechas seleccionadas.");

        }
        reservaRepository.save(reserva);
    }*/
