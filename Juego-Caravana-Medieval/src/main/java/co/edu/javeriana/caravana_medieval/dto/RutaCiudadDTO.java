package co.edu.javeriana.caravana_medieval.dto;

public class RutaCiudadDTO {
    private Long rutaId;
    private Long ciudadOrigenId;
    private Long ciudadDestinoId;
    
    public RutaCiudadDTO() {
    }

    public RutaCiudadDTO(Long rutaId, Long ciudadOrigenId, Long ciudadDestinoId) {
        this.rutaId = rutaId;
        this.ciudadOrigenId = ciudadOrigenId;
        this.ciudadDestinoId = ciudadDestinoId;
    }

    public Long getRutaId() {
        return rutaId;
    }

    public void setRutaId(Long rutaId) {
        this.rutaId = rutaId;
    }

    public Long getCiudadOrigenId() {
        return ciudadOrigenId;
    }

    public void setCiudadOrigenId(Long ciudadOrigenId) {
        this.ciudadOrigenId = ciudadOrigenId;
    }

    public Long getCiudadDestinoId() {
        return ciudadDestinoId;
    }

    public void setCiudadDestinoId(Long ciudadDestinoId) {
        this.ciudadDestinoId = ciudadDestinoId;
    }
    
    
}
