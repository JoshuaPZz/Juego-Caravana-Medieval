package co.edu.javeriana.caravana_medieval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval.repository.JugadorRepository;
import co.edu.javeriana.caravana_medieval.dto.JugadorDTO;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.Jugador;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JugadorService {
    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private CaravanaRepository caravanaRepository;
    
    public List<JugadorDTO> getAllJugadores() {
        return jugadorRepository.findAll().stream()
                .map(jugador -> new JugadorDTO(jugador.getId(), jugador.getNombre(), jugador.getRol(),caravanaRepository.findById(jugador.getCaravana().getId()).get().getId()))
                .toList();
    }

    public JugadorDTO getJugadorById(Long id) {
        return jugadorRepository.findById(id)
                .map(jugador -> new JugadorDTO(jugador.getId(), jugador.getNombre(), jugador.getRol(),jugador.getCaravana().getId()))
                .orElse(null);
    }

    public String getCaravanaIdByJugadorId(Long id) {
        return jugadorRepository.findById(id)
                .map(jugador -> jugador.getCaravana().getId().toString())
                .orElse(null);
    }
    
    
}
