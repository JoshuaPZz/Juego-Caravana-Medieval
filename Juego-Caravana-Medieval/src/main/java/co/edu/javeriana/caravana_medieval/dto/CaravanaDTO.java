package co.edu.javeriana.caravana_medieval.dto;

import java.time.Duration;
import java.time.LocalTime;

public class CaravanaDTO {
    private Long id;
    private String nombre;
    private int velocidad;
    private int capacidadMax;
    private double dineroDisponible;
    private int puntosVida; 
    private LocalTime horaViaje; 
    private boolean tieneGuardias;
    private long tiempoTranscurrido;

    public CaravanaDTO() {
    }

    public CaravanaDTO(Long id, String nombre, int velocidad, int capacidadMax, double dineroDisponible,
                       int puntosVida, LocalTime horaViaje, boolean tieneGuardias, long tiempoTranscurrido) {
        this.id = id;
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.capacidadMax = capacidadMax;
        this.dineroDisponible = dineroDisponible;
        this.puntosVida = puntosVida;
        this.horaViaje = horaViaje;
        this.tieneGuardias = tieneGuardias;
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public long getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public void setTiempoTranscurrido(long tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public LocalTime getHoraViaje() {
        return horaViaje;
    }

    public void setHoraViaje(LocalTime horaViaje) {
        this.horaViaje = horaViaje;
    }

    public boolean isTieneGuardias() {
        return tieneGuardias;
    }

    public void setTieneGuardias(boolean tieneGuardias) {
        this.tieneGuardias = tieneGuardias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public double getDineroDisponible() {
        return dineroDisponible;
    }

    public void setDineroDisponible(double dineroDisponible) {
        this.dineroDisponible = dineroDisponible;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }
}
