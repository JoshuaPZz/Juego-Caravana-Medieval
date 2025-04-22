package co.edu.javeriana.caravana_medieval.service;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadRutasDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaCiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval.mapper.CaravanaMapper;
import co.edu.javeriana.caravana_medieval.mapper.CiudadMapper;
import co.edu.javeriana.caravana_medieval.mapper.RutaMapper;
import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.RutaRepository;
import jakarta.transaction.Transactional;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.model.Ruta;

@Service
public class ViajeService {

    @Autowired
    private CaravanaRepository caravanaRepository;
    @Autowired
    private CaravanaService caravanaService;
    @Autowired
    private CiudadRutaService ciudadRutaService;
    @Autowired
    private RutaService rutaService;

    public CiudadDTO getCiudadActual(Long id) {
        Ciudad ciudad = caravanaService.getCiudadActual(id);
        CiudadMapper ciudadMapper = new CiudadMapper();
        CiudadDTO ciudadDTO = ciudadMapper.toDTO(ciudad);
        return ciudadDTO;
    }

    @Transactional
    public CaravanaDTO viajar(Long idCaravana, Long idCiudadDestino, Long idRuta) {
        // Obtener entidades necesarias
        Caravana caravana = caravanaService.getCaravanaById(idCaravana);
        Ciudad ciudadActual = caravanaService.getCiudadActual(idCaravana);
        Ciudad ciudadDestino = ciudadRutaService.getCiudadDestinoByid(idCiudadDestino);
        Optional<RutaDTO> rutaDto = rutaService.buscarRuta(idRuta);
        Ruta ruta = rutaDto.map(RutaMapper::toEntity)
                .orElseThrow(() -> new IllegalArgumentException("Ruta no encontrada con ID: " + idRuta));

        // Validaciones iniciales
        if (caravana.getPuntosVida() - ruta.getDano() <= 0) {
            throw new IllegalArgumentException(
                    "La caravana no tiene suficientes puntos de vida para viajar por esta ruta.");
        }

        if (ciudadActual.getId().equals(ciudadDestino.getId())) {
            throw new IllegalArgumentException("La ciudad actual y la ciudad destino son las mismas.");
        }

        if (!ciudadActual.getId().equals(caravana.getCiudadActual().getId())) {
            throw new IllegalArgumentException("La ciudad actual no es la misma que la ciudad de la caravana.");
        }

        // Verificar que la ruta conecta las ciudades
        boolean rutaOrigenValida = ciudadActual.getRutasOrigen().stream()
                .anyMatch(r -> r.getId().equals(ruta.getId()));

        boolean rutaDestinoValida = ciudadDestino.getRutasDestino().stream()
                .anyMatch(r -> r.getId().equals(ruta.getId()));

        // Actualizar los datos de la caravana
        int dano = 0;
        if(caravana.isTieneGuardias()){
            dano = (int) Math.round(ruta.getDano() * 0.75);
        } else {
            dano = ruta.getDano();
        }     
        // Calcular la duración del viaje
        Duration tiempoViaje = Duration.ofHours(ruta.getLongitud() / caravana.getVelocidad());
        caravana.setPuntosVida(caravana.getPuntosVida() - dano);
        caravana.setHoraViaje(caravana.getHoraViaje().plusHours(tiempoViaje.toHours()));
        caravana.setTiempoTranscurrido(caravana.getTiempoTranscurrido().plus(tiempoViaje));
        if(caravana.getTiempoTranscurrido().compareTo(Duration.ofDays(5)) > 0){
            throw new IllegalArgumentException("El tiempo se acabó.");

        }
        caravana.setCiudadActual(ciudadDestino);

        caravana = caravanaRepository.saveAndFlush(caravana);

        System.out.println("Caravana actualizada y guardada correctamente. ID: " + caravana.getId() +
                ", Ciudad actual: " + caravana.getCiudadActual().getNombre() +
                ", Puntos de vida: " + caravana.getPuntosVida());

        return CaravanaMapper.toDTO(caravana);
    }

}
