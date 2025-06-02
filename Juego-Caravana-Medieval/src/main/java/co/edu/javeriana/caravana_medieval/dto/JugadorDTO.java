package co.edu.javeriana.caravana_medieval.dto;

import co.edu.javeriana.caravana_medieval.model.Role;

public class JugadorDTO {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private Role role;
    private Long idCaravana;

    public JugadorDTO() {
    }

    public JugadorDTO(Long id, String nombre, String email, String password, Role role, Long idCaravana) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.role = role;
        this.idCaravana = idCaravana;
    }

    public JugadorDTO(Long id, String nombre, String email, Role role, Long idCaravana) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.role = role;
        this.idCaravana = idCaravana;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getIdCaravana() {
        return idCaravana;
    }

    public void setIdCaravana(Long idCaravana) {
        this.idCaravana = idCaravana;
    }
}