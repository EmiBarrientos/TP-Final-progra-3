package com.example.demo.service;

import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.dto.PasajeroDTO;
import com.example.demo.dto.crear.EmpleadoConUsuarioCreadoCrearDTO;
import com.example.demo.dto.crear.EmpleadoCrearDTO;
import com.example.demo.dto.crear.PasajeroConUsuarioCreadoCrearDTO;
import com.example.demo.mapper.EmpleadoMapper;
import com.example.demo.mapper.noIdenticos.EmpleadoConUsuarioCreadoCrearMapper;
import com.example.demo.mapper.noIdenticos.EmpleadoCrearMapper;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.Empleado;
import com.example.demo.model.Pasajero;
import com.example.demo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;
    private final EmpleadoCrearMapper empleadoCrearMapper;
    private final EmpleadoConUsuarioCreadoCrearMapper empleadoConUsuarioCreadoCrearMapper;

    @Autowired


    public EmpleadoService(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper, EmpleadoCrearMapper empleadoCrearMapper, EmpleadoConUsuarioCreadoCrearMapper empleadoConUsuarioCreadoCrearMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
        this.empleadoCrearMapper = empleadoCrearMapper;
        this.empleadoConUsuarioCreadoCrearMapper = empleadoConUsuarioCreadoCrearMapper;
    }

    public List<EmpleadoDTO> findAll() {
        return empleadoRepository.findAll()
                .stream().map(p->empleadoMapper.toDto(p)).toList();
    }

    public Optional<EmpleadoDTO> findById(Long id) {
        EmpleadoDTO empleadoDTO = empleadoMapper.toDto(empleadoRepository.findById(id).get());
        return Optional.ofNullable(empleadoDTO);
    }



    public Optional<EmpleadoDTO> save(EmpleadoConUsuarioCreadoCrearDTO empleadoCrearDTO) {
        Empleado empleado = empleadoConUsuarioCreadoCrearMapper.toEntity(empleadoCrearDTO);
        EmpleadoDTO pasajeroDTO = empleadoMapper.toDto(empleadoRepository.save(empleado));
        return Optional.of(pasajeroDTO);
    }




    /*    public Optional<EmpleadoDTO> save(EmpleadoCrearDTO empleadoDTO) {
        Empleado empleado = empleadoCrearMapper.toEntity(empleadoDTO);
        EmpleadoDTO empleadoDTO2 = empleadoMapper.toDto(empleadoRepository.save(empleado));
        return Optional.ofNullable(empleadoDTO2);
    }
*/
    public void deleteById(Long id) {
        empleadoRepository.deleteById(id);
    }

    public Optional<EmpleadoDTO> updateEmpledo(Long id, EmpleadoConUsuarioCreadoCrearDTO dtoCrearDetails) {
        //dtoCrearDetails.setId(id); // no puede modificar el id
        Empleado modelCrearDetails = empleadoConUsuarioCreadoCrearMapper.toEntity(dtoCrearDetails); //creamos el modelo con los cambios y el resto null
        Optional<Empleado> model = empleadoRepository.findById(id); //buscamos el model a cambiar
        if (model.isPresent()) {
            Empleado updatedModel = model.get(); // el model a cambiar
            ReflectionMapper.actualizarCamposNoNulos(modelCrearDetails,updatedModel); // actualizamos el model
            updatedModel = empleadoRepository.save(updatedModel);
            Optional<EmpleadoDTO> respuesta = Optional.of(empleadoMapper.toDto(updatedModel));
            return respuesta;
        } else {
            return Optional.empty();
        }
    }




/*    public Optional<EmpleadoDTO> updateEmpleado(Long id, EmpleadoCrearDTO dtoCrearDetails) {
        dtoCrearDetails.setId(id); // no puede modificar el id
        Empleado modelCrearDetails = empleadoCrearMapper.toEntity(dtoCrearDetails); //creamos el modelo con los cambios y el resto null
        Optional<Empleado> model = empleadoRepository.findById(id); //buscamos el model a cambiar
        if (model.isPresent()) {
            Empleado updatedModel = model.get(); // el model a cambiar
            ReflectionMapper.actualizarCamposNoNulos(modelCrearDetails,updatedModel); // actualizamos el model
            empleadoRepository.save(updatedModel);
            Optional<EmpleadoDTO> respuesta = Optional.ofNullable(empleadoMapper.toDto(updatedModel));
            return respuesta;
        } else {
            return Optional.ofNullable(null);
        }
    }*/

}