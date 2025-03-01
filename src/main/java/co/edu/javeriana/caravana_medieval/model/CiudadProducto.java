package co.edu.javeriana.caravana_medieval.model;

import jakarta.persistence.*;

@Entity
public class CiudadProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCiudad", nullable = false)
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    private double factorDemanda;
    private double factorOferta;
    private double precioCompra;
    private double precioVenta;

    public CiudadProducto() {}

    public CiudadProducto(Ciudad ciudad, Producto producto, double factorDemanda, double factorOferta) {
        this.ciudad = ciudad;
        this.producto = producto;
        this.factorDemanda = factorDemanda;
        this.factorOferta = factorOferta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getFactorDemanda() {
        return factorDemanda;
    }

    public void setFactorDemanda(double factorDemanda) {
        this.factorDemanda = factorDemanda;
    }

    public double getFactorOferta() {
        return factorOferta;
    }

    public void setFactorOferta(double factorOferta) {
        this.factorOferta = factorOferta;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
}
