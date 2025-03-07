package co.edu.javeriana.caravana_medieval.dto;

public class RutaDTO {

    private Long id;
    private int longitud;
    private int dano;
    
    public RutaDTO() {
    }

    public RutaDTO(Long id, int longitud, int dano) {
        this.id = id;
        this.longitud = longitud;
        this.dano = dano;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getLongitud() {
        return longitud;
    }
    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
    public int getDano() {
        return dano;
    }
    public void setDano(int dano) {
        this.dano = dano;
    }
    
    

}
