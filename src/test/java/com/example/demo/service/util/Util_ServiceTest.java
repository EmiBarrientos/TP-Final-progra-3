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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Util_ServiceTest {

    @Mock private HabitacionRepository habitacionRepository;
    @Mock private ReservaRepository reservaRepository;
    @Mock private EmpleadoRepository empleadoRepository;
    @Mock private PasajeroRepository pasajeroRepository;
    @Mock private Costo_ServicioRepository costoServicioRepository;
    @Mock private UsuarioRepository usuarioRepository;

    @Mock private CostoHabitacionService costoHabitacionService;
    @Mock private HabitacionMapper habitacionMapper;
    @Mock private PasajeroCrearMapper pasajeroCrearMapper;
    @Mock private PasajeroMapper pasajeroMapper;
    @Mock private EmpleadoCrearMapper empleadoCrearMapper;
    @Mock private EmpleadoMapper empleadoMapper;

    @InjectMocks
    private Util_Service utilService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavePasajeroUsuario() {
        PasajeroCrearDTO dto = new PasajeroCrearDTO();
        Pasajero pasajero = new Pasajero();
        pasajero.setUsuario(new Usuario());
        PasajeroDTO pasajeroDTO = new PasajeroDTO();

        when(pasajeroCrearMapper.toEntity(dto)).thenReturn(pasajero);
        when(usuarioRepository.save(any())).thenReturn(pasajero.getUsuario());
        when(pasajeroRepository.save(any())).thenReturn(pasajero);
        when(pasajeroMapper.toDto(any())).thenReturn(pasajeroDTO);

        Optional<PasajeroDTO> result = utilService.savePasajeroUsuario(dto);
        assertTrue(result.isPresent());
        assertEquals(pasajeroDTO, result.get());
    }

    @Test
    void testSaveEmpleadoUsuario() {
        EmpleadoCrearDTO dto = new EmpleadoCrearDTO();
        Empleado empleado = new Empleado();
        empleado.setUsuario(new Usuario());
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();

        when(empleadoCrearMapper.toEntity(dto)).thenReturn(empleado);
        when(usuarioRepository.save(any())).thenReturn(empleado.getUsuario());
        when(empleadoRepository.save(any())).thenReturn(empleado);
        when(empleadoMapper.toDto(any())).thenReturn(empleadoDTO);

        Optional<EmpleadoDTO> result = utilService.saveEmpleadoUsuario(dto);
        assertTrue(result.isPresent());
        assertEquals(empleadoDTO, result.get());
    }

    @Test
    void testAsignarPasajeroAReserva() {
        Long reservaId = 1L, pasajeroId = 2L;
        Reserva reserva = new Reserva();
        Pasajero pasajero = new Pasajero();

        when(pasajeroRepository.findById(pasajeroId)).thenReturn(Optional.of(pasajero));
        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reserva));

        assertDoesNotThrow(() -> utilService.asignarPasajeroAReserva(reservaId, pasajeroId));
        verify(reservaRepository).save(reserva);
    }

    @Test
    void testObtenerHabitacionesDisponiblesTodas() {
        LocalDate inicio = LocalDate.now();
        LocalDate fin = inicio.plusDays(2);
        Habitacion habitacion = new Habitacion();
        HabitacionDTO dto = new HabitacionDTO();

        when(reservaRepository.findHabitacionIdsReservadasEntreFechas(inicio, fin)).thenReturn(List.of());
        when(habitacionRepository.findAll()).thenReturn(List.of(habitacion));
        when(habitacionMapper.toDto(habitacion)).thenReturn(dto);

        List<HabitacionDTO> disponibles = utilService.obtenerHabitacionesDisponibles(inicio, fin);
        assertEquals(1, disponibles.size());
    }

    @Test
    void testCalcularCostoTotal() {
        Habitacion habitacion = new Habitacion();
        habitacion.setTipoHabitacion(TipoHabitacion.DOBLE);
        habitacion.setServicios(Map.of(ServicioEnum.TV_CABLE, true));

        when(habitacionRepository.findByNumeroHabitacion("101")).thenReturn(habitacion);
        when(costoHabitacionService.findByTipoHabitacion("DOBLE"))
                .thenReturn(Optional.of(new CostoHabitacionDTO(null, TipoHabitacion.DOBLE, 100.0)));
        when(costoServicioRepository.findByNombre(ServicioEnum.TV_CABLE))
                .thenReturn(new Costo_Servicio(1L, ServicioEnum.TV_CABLE, 25.0));

        Double costo = utilService.calcularCostoTotal("101");
        assertEquals(125.0, costo);
    }
}
