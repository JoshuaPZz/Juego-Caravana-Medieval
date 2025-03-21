package co.edu.javeriana.caravana_medieval.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
    private List<CiudadProducto> productos = new ArrayList<>();

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
    private List<CiudadServicio> servicios = new ArrayList<>();

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
    private List<ServicioCompra> compras = new ArrayList<>();

    @OneToMany(mappedBy = "origen", cascade = CascadeType.ALL)
    private List<Ruta> rutasOrigen = new ArrayList<>();

    @OneToMany(mappedBy = "destino", cascade = CascadeType.ALL)
    private List<Ruta> rutasDestino = new ArrayList<>();

    private String nombre;
    private int impuesto;

    public Ciudad() {
    }

    public Ciudad(String nombre, int impuesto) {
        this.nombre = nombre;
        this.impuesto = impuesto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CiudadProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<CiudadProducto> productos) {
        this.productos = productos;
    }

    public List<CiudadServicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<CiudadServicio> servicios) {
        this.servicios = servicios;
    }

    public List<ServicioCompra> getCompras() {
        return compras;
    }

    public void setCompras(List<ServicioCompra> compras) {
        this.compras = compras;
    }

    public List<Ruta> getRutasOrigen() {
        return rutasOrigen;
    }

    public void setRutasOrigen(List<Ruta> rutasOrigen) {
        this.rutasOrigen = rutasOrigen;
    }

    public List<Ruta> getRutasDestino() {
        return rutasDestino;
    }

    public void setRutasDestino(List<Ruta> rutasDestino) {
        this.rutasDestino = rutasDestino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(int impuesto) {
        this.impuesto = impuesto;
    }
}
