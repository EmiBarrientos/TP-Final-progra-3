package com.example.demo.controller;

import com.example.demo.dto.PasajeroDTO;
import com.example.demo.dto.crear.PasajeroConUsuarioCreadoCrearDTO;
import com.example.demo.dto.crear.PasajeroCrearDTO;
import com.example.demo.mapper.PasajeroMapper;
import com.example.demo.mapper.noIdenticos.PasajeroCrearMapper;
import com.example.demo.model.Pasajero;
import com.example.demo.service.PasajeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pasajeros")
@RequiredArgsConstructor
public class PasajeroController {
    @Autowired
    private PasajeroService pasajeroService;
    @Autowired
    private PasajeroCrearMapper pasajeroCrearMapper;
    @Autowired
    private PasajeroMapper pasajeroMapper;

    @GetMapping
    public List<Pasajero> getAllPasajeros() {
        return pasajeroService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PasajeroDTO> getPasajeroById(@PathVariable Long id) {
        Optional<PasajeroDTO> pasajero = pasajeroService.findById(id);
        return pasajero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PostMapping
    public PasajeroDTO createPasajero(@RequestBody PasajeroConUsuarioCreadoCrearDTO pasajero) {
        //Pasajero pasajero = pasajeroCrearMapper.toEntity(pasajeroCrearDTO);
        //PasajeroDTO pasajeroDTO = pasajeroMapper.toDto(pasajero);
        return pasajeroService.save(pasajero).get();
    }




    @PutMapping("/{id}")
    public PasajeroDTO updatePasajero(@PathVariable Long id,
                                      @RequestBody PasajeroConUsuarioCreadoCrearDTO pasajeroDetails) {
        return pasajeroService.updatePasajero(id,pasajeroDetails).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePasajero(@PathVariable Long id) {
        pasajeroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}