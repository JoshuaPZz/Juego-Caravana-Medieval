package co.edu.javeriana.caravana_medieval.dto;

import java.util.List;

public class CiudadDTO {
    private Long id;
    private String nombre;
    private int impuesto;
    private List<String> productoNombres;
    private List<String> servicioNombres;
    private List<String> compraServicioNombres;
    private List<String> compraCaravanaNombres;
    private List<String> rutaOrigenNombres;
    private List<String> rutaDestinoNombres;


    public CiudadDTO(){
    }

    public CiudadDTO(Long id, String nombre, int impuesto, List<String> productoNombres, List<String> servicioNombres, List<String> compraServicioNombres, List<String> compraCaravanaNombres, List<String> rutaOrigenNombres, List<String> rutaDestinoNombres) {
        this.id = id;
        this.nombre = nombre;
        this.impuesto = impuesto;
        this.productoNombres = productoNombres;
        this.servicioNombres = servicioNombres;
        this.compraServicioNombres = compraServicioNombres;
        this.compraCaravanaNombres = compraCaravanaNombres;
        this.rutaOrigenNombres = rutaOrigenNombres;
        this.rutaDestinoNombres = rutaDestinoNombres;
    }

    public List<String> getRutaOrigenNombres() {
        return rutaOrigenNombres;
    }

    public void setRutaOrigenNombres(List<String> rutaOrigenNombres) {
        this.rutaOrigenNombres = rutaOrigenNombres;
    }

    public List<String> getRutaDestinoNombres() {
        return rutaDestinoNombres;
    }

    public void setRutaDestinoNombres(List<String> rutaDestinoNombres) {
        this.rutaDestinoNombres = rutaDestinoNombres;
    }

    public void setCompraServicioNombres(List<String> compraServicioNombres) {
        this.compraServicioNombres = compraServicioNombres;
    }

    public List<String> getCompraCaravanaNombres() {
        return compraCaravanaNombres;
    }

    public void setCompraCaravanaNombres(List<String> compraCaravanaNombres) {
        this.compraCaravanaNombres = compraCaravanaNombres;
    }

    public List<String> getCompraServicioNombres() {
        return compraServicioNombres;
    }

    public void setCompraServicioNombre(List<String> compraServicioNombres) {
        this.compraServicioNombres = compraServicioNombres;
    }

    public List<String> getServicioNombres() {
        return servicioNombres;
    }

    public void setServicioNombres(List<String> servicioNombres) {
        this.servicioNombres = servicioNombres;
    }

    public List<String> getProductoNombres() {
        return productoNombres;
    }

    public void setProductoNombres(List<String> productoNombres) {
        this.productoNombres = productoNombres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(int impuesto) {
        this.impuesto = impuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
