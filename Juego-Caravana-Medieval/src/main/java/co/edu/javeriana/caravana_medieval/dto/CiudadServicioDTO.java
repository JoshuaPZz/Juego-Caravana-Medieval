package co.edu.javeriana.caravana_medieval.dto;

import java.util.List;

public class CiudadServicioDTO {
    private Long id;
    private Long idCiudad;
    private String nombreServicio;
    private Long idServicio;
    private double precio;

    public CiudadServicioDTO() {
    }
    public CiudadServicioDTO(Long id, Long idCiudad, Long idServicio, double precio, String nombreServicio) {
        this.id = id;
        this.idCiudad = idCiudad;
        this.idServicio = idServicio;
        this.precio = precio;
        this.nombreServicio = nombreServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdCiudad() {
        return idCiudad;
    }
    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }
    public Long getIdServicio() {
        return idServicio;
    }
    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    



    
}
