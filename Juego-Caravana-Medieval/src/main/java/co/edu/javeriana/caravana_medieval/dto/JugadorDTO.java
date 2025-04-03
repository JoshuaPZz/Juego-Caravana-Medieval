package co.edu.javeriana.caravana_medieval.dto;

public class JugadorDTO {
    private Long id;
    private String rol;
    private String nombre;
    private Long idCaravana;

    public JugadorDTO() {
    }

    public JugadorDTO(Long id, String rol, String nombre, Long idCaravana) {
        this.id = id;
        this.rol = rol;
        this.nombre = nombre;
        this.idCaravana = idCaravana;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
}
