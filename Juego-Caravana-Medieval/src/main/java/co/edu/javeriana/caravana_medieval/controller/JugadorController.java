package co.edu.javeriana.caravana_medieval.controller;

import co.edu.javeriana.caravana_medieval.dto.JugadorDTO;
import co.edu.javeriana.caravana_medieval.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jugador")
public class JugadorController {
    @Autowired
    private JugadorService jugadorService;

    @GetMapping
    public List<JugadorDTO> getAllJugadores() {
        return jugadorService.getAllJugadores();
    }

    @GetMapping("/{id}")
    public JugadorDTO getJugadorById(@PathVariable Long id) {
        return jugadorService.getJugadorById(id);
    }

    @GetMapping("/{id}/caravana")
    public String getCaravanaIdByJugadorId(@PathVariable Long id) {
        return jugadorService.getCaravanaIdByJugadorId(id);
    }

    @GetMapping("/email/{correo}")
    public JugadorDTO getJugadorbyEmail(@PathVariable String correo) {
        return jugadorService.getJugadorbyEmail(correo);
    }
}