package com.example.demo.service;

import com.example.demo.model.Credenciales;
import com.example.demo.repository.CredencialesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CredencialesServiceOJO {
    private final CredencialesRepository credencialesRepository;

    @Autowired
    public CredencialesServiceOJO(CredencialesRepository credencialesRepository) {
        this.credencialesRepository = credencialesRepository;
    }

    public Credenciales save(Credenciales credenciales) {
        return credencialesRepository.save(credenciales);
    }

    public Optional<Credenciales> findById(Long id) {
        return credencialesRepository.findById(id);
    }

    public Credenciales findByNombreUsuario(String nombreUsuario) {
        return credencialesRepository.findByNombreUsuario(nombreUsuario);
    }

    public void deleteById(Long id) {
        credencialesRepository.deleteById(id);
    }
}
