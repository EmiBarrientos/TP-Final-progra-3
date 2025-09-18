package com.example.demo.dto;

import java.time.LocalDateTime;

public class RespuestasDTO<T> {
    private boolean exito;
    private String mensaje;
    private LocalDateTime timestamp;
    private T datos;

    public RespuestasDTO() {
        this.timestamp = LocalDateTime.now();
    }

    public RespuestasDTO(boolean exito, String mensaje, T datos) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
        this.datos = datos;
    }

    public boolean isExito() { return exito; }
    public void setExito(boolean exito) { this.exito = exito; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public T getDatos() { return datos; }
    public void setDatos(T datos) { this.datos = datos; }
}