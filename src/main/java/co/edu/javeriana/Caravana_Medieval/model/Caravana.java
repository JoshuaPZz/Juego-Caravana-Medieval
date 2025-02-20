package co.edu.javeriana.caravana_medieval.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Caravana {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "caravana", cascade = CascadeType.ALL)
    private List<Jugador> jugadores = new ArrayList<>();

    @OneToMany(mappedBy = "caravana", cascade = CascadeType.ALL)
    private List<CaravanaProducto> productos = new ArrayList<>();

    @OneToMany(mappedBy = "caravana", cascade = CascadeType.ALL)
    private List<ServicioCompra> compras = new ArrayList<>();

    private String nombre;
    private int velocidad;
    private int capacidadMax;
    private int dineroDisponible;
    private int puntosVida;

    public Caravana() {
    }

    public Caravana(String nombre, int velocidad, int capacidadMax, int dineroDisponible, int puntosVida) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.capacidadMax = capacidadMax;
        this.dineroDisponible = dineroDisponible;
        this.puntosVida = puntosVida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<CaravanaProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<CaravanaProducto> productos) {
        this.productos = productos;
    }

    public List<ServicioCompra> getCompras() {
        return compras;
    }

    public void setCompras(List<ServicioCompra> compras) {
        this.compras = compras;
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

    public int getDineroDisponible() {
        return dineroDisponible;
    }

    public void setDineroDisponible(int dineroDisponible) {
        this.dineroDisponible = dineroDisponible;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }
}
