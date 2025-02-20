package co.edu.javeriana.caravana_medieval.model;

import jakarta.persistence.*;

@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String rol;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idCaravana", nullable = false)
    private Caravana caravana;

    public Jugador() {
    }

    public Jugador(String rol, String nombre, Caravana caravana) {
        this.rol = rol;
        this.nombre = nombre;
        this.caravana = caravana;
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

    public Caravana getCaravana() {
        return caravana;
    }

    public void setCaravana(Caravana caravana) {
        this.caravana = caravana;
    }
}
