package co.edu.javeriana.caravana_medieval.dto;

public class ServicioCompraDTO {
    private Long id;
    private Long idCiudad;
    private Long idServicio;
    private Long idCaravana;
    private String nombreServicio;
    private String nombreCaravana;

    public ServicioCompraDTO() {}
    public ServicioCompraDTO(Long id,Long idCiudad, Long idServicio, Long idCaravana) {
         this.id = id;
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
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
