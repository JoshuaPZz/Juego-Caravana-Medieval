package co.edu.javeriana.caravana_medieval.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private int tipo;
    private String descripcion;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<CaravanaProducto> caravanas = new ArrayList<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<CiudadProducto> ciudades = new ArrayList<>();

    public Producto() {
    }

    public Producto(String nombre, int tipo, String descripcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<CaravanaProducto> getCaravanas() {
        return caravanas;
    }

    public void setCaravanas(List<CaravanaProducto> caravanas) {
        this.caravanas = caravanas;
    }

    public List<CiudadProducto> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<CiudadProducto> ciudades) {
        this.ciudades = ciudades;
    }
}
