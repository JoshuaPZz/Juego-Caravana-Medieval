package co.edu.javeriana.caravana_medieval.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    private List<CiudadServicio> ciudades = new ArrayList<>();

    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    private List<ServicioCompra> compras = new ArrayList<>();

    public Servicio() {
    }

    public Servicio(String nombre, String descripcion) {
        this.nombre = nombre;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<CiudadServicio> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<CiudadServicio> ciudades) {
        this.ciudades = ciudades;
    }

    public List<ServicioCompra> getCompras() {
        return compras;
    }

    public void setCompras(List<ServicioCompra> compras) {
        this.compras = compras;
    }
}
