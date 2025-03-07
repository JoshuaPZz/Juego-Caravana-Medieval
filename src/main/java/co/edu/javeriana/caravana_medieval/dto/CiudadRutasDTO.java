package co.edu.javeriana.caravana_medieval.dto;

import java.util.List;

public class CiudadRutasDTO {
    private Long id;
    private List<Long> idRutasOrigen;
    private List<Long> idRutasDestino;

    public CiudadRutasDTO() {
        
    }

    public CiudadRutasDTO(Long id, List<Long> idRutasOrigen, List<Long> idRutasDestino) {
        this.id = id;
        this.idRutasOrigen = idRutasOrigen;
        this.idRutasDestino = idRutasDestino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIdRutasOrigen() {
        return idRutasOrigen;
    }

    public void setIdRutasOrigen(List<Long> idRutasOrigen) {
        this.idRutasOrigen = idRutasOrigen;
    }

    public List<Long> getIdRutasDestino() {
        return idRutasDestino;
    }

    public void setIdRutasDestino(List<Long> idRutasDestino) {
        this.idRutasDestino = idRutasDestino;
    }

    
    
}
