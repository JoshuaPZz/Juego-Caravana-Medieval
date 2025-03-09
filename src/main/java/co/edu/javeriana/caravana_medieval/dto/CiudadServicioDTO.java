package co.edu.javeriana.caravana_medieval.dto;

import java.util.List;

public class CiudadServicioDTO {
    private Long idCiudad;
    private List<Long> idServicios;

    public CiudadServicioDTO() {}

    public CiudadServicioDTO(Long idCiudad, List<Long> idServicios) {
        this.idCiudad = idCiudad;
        this.idServicios = idServicios;
    }

    public Long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }

    public List<Long> getIdServicios() {
        return idServicios;
    }

    public void setIdServicios(List<Long> idServicios) {
        this.idServicios = idServicios;
    }
}
