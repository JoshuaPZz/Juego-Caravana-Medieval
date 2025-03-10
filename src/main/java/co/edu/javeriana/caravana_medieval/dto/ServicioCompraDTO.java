package co.edu.javeriana.caravana_medieval.dto;

public class ServicioCompraDTO {
    private Long idCiudad;
    private Long idServicio;
    private Long idCaravana;
    private String nombreServicio;
    private String nombreCaravana;

    public ServicioCompraDTO() {}
    public ServicioCompraDTO(Long idCiudad, Long idServicio, Long idCaravana) {
        this.idCiudad = idCiudad;
        this.idServicio = idServicio;
        this.idCaravana = idCaravana;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getNombreCaravana() {
        return nombreCaravana;
    }

    public void setNombreCaravana(String nombreCaravana) {
        this.nombreCaravana = nombreCaravana;
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

    public Long getIdCaravana() {
        return idCaravana;
    }

    public void setIdCaravana(Long idCaravana) {
        this.idCaravana = idCaravana;
    }
}
