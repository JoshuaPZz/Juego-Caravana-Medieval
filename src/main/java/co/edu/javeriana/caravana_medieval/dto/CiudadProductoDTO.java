package co.edu.javeriana.caravana_medieval.dto;

import java.util.List;

public class CiudadProductoDTO {
    private Long idCiudad;
    private List<Long> idProductos;

    public CiudadProductoDTO(){

    }
    public CiudadProductoDTO(Long idCiudad, List<Long> idProductos) {
        this.idCiudad = idCiudad;
        this.idProductos = idProductos;
    }

    public Long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }

    public List<Long> getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(List<Long> idProductos) {
        this.idProductos = idProductos;
    }
}
