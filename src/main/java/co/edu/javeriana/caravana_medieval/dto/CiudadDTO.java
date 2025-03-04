package co.edu.javeriana.caravana_medieval.dto;

public class CiudadDTO {
    private String nombre;
    private int impuesto;

    public CiudadDTO() {
    }

    public CiudadDTO(String nombre, int impuesto) {
        this.nombre = nombre;
        this.impuesto = impuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImpuesto() {
        return impuesto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImpuesto(int impuesto) {
        this.impuesto = impuesto;
    }
    
    
}
