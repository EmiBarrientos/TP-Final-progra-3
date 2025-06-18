package com.example.demo.service;

import com.example.demo.dto.HabitacionDTO;
import com.example.demo.dto.crear.HabitacionCrearDTO;
import com.example.demo.mapper.HabitacionMapper;
import com.example.demo.mapper.noIdenticos.HabitacionCrearMapper;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.Habitacion;
import com.example.demo.model.enums.EstadoHabitacion;
import com.example.demo.model.enums.TipoHabitacion;
import com.example.demo.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final HabitacionMapper habitacionMapper;
    private final HabitacionCrearMapper habitacionCrearMapper;
    //    private final Costo_ServicioRepository costoServicioRepository;
//    private final CostoHabitacionService costoHabitacionService;

    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository, HabitacionMapper habitacionMapper, HabitacionCrearMapper habitacionCrearMapper) {
        this.habitacionRepository = habitacionRepository;
        this.habitacionMapper = habitacionMapper;
        this.habitacionCrearMapper = habitacionCrearMapper;
    }

    // Métodos básicos de CRUD
    public List<HabitacionDTO> findAll() {
        return habitacionRepository.findAll()
                .stream().map(p->habitacionMapper.toDto(p)).toList();
    }

    public Optional<HabitacionDTO> findById(Long id) {
        Optional <Habitacion> habitacion = Optional.of(habitacionRepository.findById(id).get());
        if(!habitacion.isEmpty()){
        HabitacionDTO respuesta = habitacionMapper.toDto(habitacion.get());
        return Optional.of(respuesta);}
        return Optional.empty();
    }

    public Optional<HabitacionDTO> save(HabitacionCrearDTO habitacionDTO) {
        Habitacion habitacion = habitacionCrearMapper.toEntity(habitacionDTO);
        HabitacionDTO respuesta = habitacionMapper.toDto(habitacionRepository.save(habitacion));
        return Optional.ofNullable(respuesta);
    }

    public void deleteById(Long id) {
        habitacionRepository.deleteById(id);
    }

    // Métodos de búsqueda específicos
    public List<HabitacionDTO> findByEstado(String estado) {
        EstadoHabitacion estadoEnum = EstadoHabitacion.valueOf(estado.toUpperCase());
        return habitacionRepository.findByEstado(estadoEnum)
                .stream().map(p->habitacionMapper.toDto(p)).toList();
    }

    public List<HabitacionDTO> findByTipoHabitacion(String tipo) {
        TipoHabitacion tipoHabitacion = TipoHabitacion.valueOf(tipo.toUpperCase());
        return habitacionRepository.findByTipoHabitacion(tipoHabitacion)
                .stream().map(p->habitacionMapper.toDto(p)).toList();
    }

    public Optional<HabitacionDTO> findByNumeroHabitacion(String numero) {
        HabitacionDTO respuesta =
                habitacionMapper.toDto(habitacionRepository.findByNumeroHabitacion(numero));
        return Optional.ofNullable(respuesta);
    }

    public Optional<HabitacionDTO> updateHabitacion(Long id, HabitacionCrearDTO dtoCrearDetails) {
        dtoCrearDetails.setId(id); // no puede modificar el id
        Habitacion modelCrearDetails = habitacionCrearMapper.toEntity(dtoCrearDetails); //creamos el modelo con los cambios y el resto null
        Optional<Habitacion> model = habitacionRepository.findById(id); //buscamos el model a cambiar
        if (model.isPresent()) {
            Habitacion updatedModel = model.get(); // el model a cambiar
            ReflectionMapper.actualizarCamposNoNulos(modelCrearDetails,updatedModel); // actualizamos el model
            habitacionRepository.save(updatedModel);
            Optional<HabitacionDTO> respuesta = Optional.ofNullable(habitacionMapper.toDto(updatedModel));
            return respuesta;
        } else {
            return Optional.ofNullable(null);
        }
    }

}