package co.edu.javeriana.caravana_medieval.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadRutasDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaCiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval.mapper.CiudadMapper;
import co.edu.javeriana.caravana_medieval.mapper.RutaMapper;
import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.RutaRepository;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.model.Ruta;

@Service
public class ViajeService {

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

    public void viajar(Long idCaravana, Long idCiudadDestino, Long idRuta){
        Caravana caravana = caravanaService.getCaravanaById(idCaravana);
        Ciudad ciudadActual = caravanaService.getCiudadActual(idCaravana);
        Ciudad ciudadDestino = ciudadRutaService.getCiudadDestinoByid(idCiudadDestino);
        Optional<RutaDTO> rutaDto = rutaService.buscarRuta(idRuta);
        Ruta ruta = rutaDto.map(RutaMapper::toEntity).orElseThrow(() -> new IllegalArgumentException("RutaDTO not found"));
        
        if(caravana.getPuntosVida()-ruta.getDano() <= 0){
            throw new IllegalArgumentException("La caravana no tiene suficientes puntos de vida para viajar por esta ruta.");
        }
        else if(ciudadActual.getId() == ciudadDestino.getId()){
            throw new IllegalArgumentException("La ciudad actual y la ciudad destino son las mismas.");
        }
        else if(ciudadActual != caravana.getCiudadActual()){
            throw new IllegalArgumentException("La ciudad actual no es la misma que la ciudad de la caravana.");
        }
        else if(!ciudadActual.getRutasOrigen().contains(ruta)){
            System.err.println("Rutas de la ciudad: " + ciudadActual.getRutasOrigen().get(0).getId() );
            System.err.println("Ruta no válida desde la ciudad actual: " + ciudadActual.getNombre() + " a " + ciudadDestino.getNombre() + " Ruta ID: " + ruta.getId());
            throw new IllegalArgumentException("La ruta no es válida desde la ciudad actual.");
        }
        else if(!ciudadDestino.getRutasDestino().contains(ruta)){
            throw new IllegalArgumentException("La ruta no es válida hacia la ciudad destino.");
        }
        else if(ciudadActual.getRutasOrigen().contains(ruta) && ciudadDestino.getRutasDestino().contains(ruta)){
            caravana.setPuntosVida(caravana.getPuntosVida()-ruta.getDano());
            caravana.setHoraViaje(caravana.getHoraViaje().plusHours(ruta.getLongitud() / caravana.getVelocidad()));
            caravana.setCiudadActual(ciudadDestino);
        }

    }



    
}
