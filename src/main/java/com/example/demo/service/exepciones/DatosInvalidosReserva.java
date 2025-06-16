package com.example.demo.service.exepciones;

public class DatosInvalidosReserva extends RuntimeException {
    public DatosInvalidosReserva(String message) {
        super(message);
    }
}
