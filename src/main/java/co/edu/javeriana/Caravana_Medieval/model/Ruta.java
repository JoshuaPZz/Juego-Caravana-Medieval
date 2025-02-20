package co.edu.javeriana.caravana_medieval.model;

import jakarta.persistence.*;

@Entity
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ciudad_origen", nullable = false)
    private Ciudad origen;

    @ManyToOne
    @JoinColumn(name = "ciudad_destino", nullable = false)
    private Ciudad destino;

    private int longitud;
    private int dano;

    public Ruta() {
    }

    public Ruta(Ciudad origen, Ciudad destino, int longitud, int dano) {
        this.origen = origen;
        this.destino = destino;
        this.longitud = longitud;
        this.dano = dano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
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
