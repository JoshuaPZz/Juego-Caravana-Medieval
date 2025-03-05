package co.edu.javeriana.caravana_medieval.dto;

public class CiudadDTO {
    private Long id;
    private String nombre;
    private int impuesto;


    public CiudadDTO(){
    }
    public CiudadDTO(Long id, String nombre, int impuesto) {
        this.id = id;
        this.nombre = nombre;
        this.impuesto = impuesto;
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
