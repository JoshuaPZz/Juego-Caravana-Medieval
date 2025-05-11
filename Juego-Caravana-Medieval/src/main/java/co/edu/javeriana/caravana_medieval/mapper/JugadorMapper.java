package co.edu.javeriana.caravana_medieval.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.caravana_medieval.dto.JugadorDTO;
import co.edu.javeriana.caravana_medieval.model.Jugador;
import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;

public class JugadorMapper {

    @Autowired
    private CaravanaRepository caravanaRepository;

    public static JugadorDTO toDTO(Jugador jugador) {
        JugadorDTO jugadorDTO = new JugadorDTO();
        jugadorDTO.setNombre(jugador.getNombre());
        jugadorDTO.setId(jugador.getId());
        jugadorDTO.setRole(jugador.getRole());
        jugadorDTO.setIdCaravana(jugador.getCaravana().getId());
        return jugadorDTO;
    }

    public Jugador toEntity(JugadorDTO jugadorDTO) {
        Jugador jugador = new Jugador();
        jugador.setNombre(jugadorDTO.getNombre());
        jugador.setId(jugadorDTO.getId());
        jugador.setRole(jugadorDTO.getRole());
        jugador.setCaravana(caravanaRepository.findById(jugadorDTO.getIdCaravana()).orElse(null));
        return jugador;
    }
}
