package com.example.demo.service;

import com.example.demo.dto.PasajeroDTO;
import com.example.demo.dto.crear.PasajeroConUsuarioCreadoCrearDTO;
import com.example.demo.dto.crear.PasajeroCrearDTO;
import com.example.demo.mapper.PasajeroMapper;
import com.example.demo.mapper.noIdenticos.PasajeroConUsuarioCreadoCrearMapper;
import com.example.demo.mapper.noIdenticos.PasajeroCrearMapper;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.Pasajero;
import com.example.demo.repository.PasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasajeroService {
    private final PasajeroRepository pasajeroRepository;
    private final PasajeroMapper pasajeroMapper;
    private final PasajeroCrearMapper pasajeroCrearMapper;
    private final PasajeroConUsuarioCreadoCrearMapper pasajeroConUsuarioCreadoCrearMapper;

    @Autowired
    public PasajeroService(PasajeroRepository pasajeroRepository, PasajeroMapper pasajeroMapper, PasajeroCrearMapper pasajeroCrearMapper, PasajeroConUsuarioCreadoCrearMapper pasajeroConUsuarioCreadoCrearMapper) {
        this.pasajeroRepository = pasajeroRepository;
        this.pasajeroMapper = pasajeroMapper;
        this.pasajeroCrearMapper = pasajeroCrearMapper;
        this.pasajeroConUsuarioCreadoCrearMapper = pasajeroConUsuarioCreadoCrearMapper;
    }

  /*  public List<PasajeroDTO> findAll() {
        return
                pasajeroRepository.findAll()
                        .stream().map(p->pasajeroMapper.toDto(p)).toList();
    }
*/
    public List<Pasajero> findAll() {
        return
                pasajeroRepository.findAll();
    }

    public Optional<PasajeroDTO> findById(Long id) {
        PasajeroDTO pasajeroDTO = pasajeroMapper.toDto(pasajeroRepository.findById(id).get());
        return Optional.of(pasajeroDTO);
    }


    public Optional<PasajeroDTO> save(PasajeroConUsuarioCreadoCrearDTO pasajeroCrearDTO) {
        Pasajero pasajero = pasajeroConUsuarioCreadoCrearMapper.toEntity(pasajeroCrearDTO);
        PasajeroDTO pasajeroDTO = pasajeroMapper.toDto(pasajeroRepository.save(pasajero));
        return Optional.of(pasajeroDTO);
    }


    public void deleteById(Long id) {
        pasajeroRepository.deleteById(id);
    }

    public Optional<PasajeroDTO> updatePasajero(Long id, PasajeroConUsuarioCreadoCrearDTO dtoCrearDetails) {
        //dtoCrearDetails.setId(id); // no puede modificar el id
        Pasajero modelCrearDetails = pasajeroConUsuarioCreadoCrearMapper.toEntity(dtoCrearDetails); //creamos el modelo con los cambios y el resto null
        Optional<Pasajero> model = pasajeroRepository.findById(id); //buscamos el model a cambiar
        if (model.isPresent()) {
            Pasajero updatedModel = model.get(); // el model a cambiar
            ReflectionMapper.actualizarCamposNoNulos(modelCrearDetails,updatedModel); // actualizamos el model
            updatedModel = pasajeroRepository.save(updatedModel);
            Optional<PasajeroDTO> respuesta = Optional.of(pasajeroMapper.toDto(updatedModel));
            return respuesta;
        } else {
            return Optional.empty();
        }
    }

}