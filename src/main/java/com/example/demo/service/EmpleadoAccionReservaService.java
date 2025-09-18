package com.example.demo.service;


import com.example.demo.dto.EmpleadoAccionReservaDTO;
import com.example.demo.dto.crear.EmpleadoAccionReservaCrearDTO;
import com.example.demo.mapper.EmpleadoAccionReservaMapper;
import com.example.demo.mapper.noIdenticos.EmpleadoAccionReservaCrearMapper;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.EmpleadoAccionReserva;
import com.example.demo.repository.EmpleadoAccionReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoAccionReservaService {
    private final EmpleadoAccionReservaRepository repository;
    private final EmpleadoAccionReservaMapper mapper;
    private final EmpleadoAccionReservaCrearMapper crearMapper;

    @Autowired
    public EmpleadoAccionReservaService(EmpleadoAccionReservaRepository repository, EmpleadoAccionReservaMapper mapper, EmpleadoAccionReservaCrearMapper crearMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.crearMapper = crearMapper;
    }


    public List<EmpleadoAccionReservaDTO> findAll() {
        return repository.findAll()
                .stream().map(p->mapper.toDto(p)).toList();
    }

    public Optional<EmpleadoAccionReservaDTO> findById(Long id) {
        EmpleadoAccionReservaDTO empleadoDTO = mapper.toDto(repository.findById(id).get());
        return Optional.ofNullable(empleadoDTO);
    }

    public Optional<EmpleadoAccionReservaDTO> save(EmpleadoAccionReservaCrearDTO empleadoDTO) {
        EmpleadoAccionReserva empleado = crearMapper.toEntity(empleadoDTO);
        EmpleadoAccionReservaDTO empleadoDTO2 = mapper.toDto(repository.save(empleado));
        return Optional.ofNullable(empleadoDTO2);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Optional<EmpleadoAccionReservaDTO> updateEmpleado(
            Long id, EmpleadoAccionReservaCrearDTO dtoCrearDetails) {
        dtoCrearDetails.setId(id); // no puede modificar el id
        EmpleadoAccionReserva modelCrearDetails = crearMapper.toEntity(dtoCrearDetails); //creamos el modelo con los cambios y el resto null
        Optional<EmpleadoAccionReserva> model = repository.findById(id); //buscamos el model a cambiar
        if (model.isPresent()) {
            EmpleadoAccionReserva updatedModel = model.get(); // el model a cambiar
            ReflectionMapper.actualizarCamposNoNulos(modelCrearDetails,updatedModel); // actualizamos el model
            repository.save(updatedModel);
            Optional<EmpleadoAccionReservaDTO> respuesta = Optional.ofNullable(mapper.toDto(updatedModel));
            return respuesta;
        } else {
            return Optional.ofNullable(null);
        }
    }




}
