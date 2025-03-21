package co.edu.javeriana.caravana_medieval.service;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.edu.javeriana.caravana_medieval.dto.RutaCiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval.mapper.CiudadMapper;
import co.edu.javeriana.caravana_medieval.mapper.RutaMapper;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.model.Ruta;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.repository.RutaRepository;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    public List<RutaDTO> listarRutas() {
        return rutaRepository.findAll().stream()
            .map(RutaMapper::toDto).toList();
    }

    public Optional<RutaDTO> buscarRuta(Long id){
        return rutaRepository.findById(id)
        .map(RutaMapper::toDto);
    }

    public Optional<RutaCiudadDTO> getRutaCiudad(Long rutaId) {
        Optional<Ruta> rutaOpt = rutaRepository.findById(rutaId);
        if (rutaOpt.isEmpty()) {
            return Optional.empty();
        }

        Ruta ruta = rutaOpt.get();
        Long origenId = (ruta.getOrigen() != null) ? ruta.getOrigen().getId() : null;
        Long destinoId = (ruta.getDestino() != null) ? ruta.getDestino().getId() : null;
    
        RutaCiudadDTO rutaCiudadDTO = new RutaCiudadDTO(rutaId, origenId, destinoId);
        return Optional.of(rutaCiudadDTO);
    }
    

    public List<RutaDTO> listaIdstoRuta (List<Long> rutasId) {
        List<RutaDTO> rutasDTO = new ArrayList<RutaDTO>();
        for(Long id : rutasId){
            rutasDTO.add(buscarRuta(id).get());
        }
        return rutasDTO;
    }

    public void guardarRuta(RutaDTO rutaDTO) {
        Ruta ruta = RutaMapper.toEntity(rutaDTO);
        rutaRepository.save(ruta);       
    }

   public void actualizarRutaCiudad(RutaCiudadDTO rutaCiudadDTO) {
    Ruta ruta = rutaRepository.findById(rutaCiudadDTO.getRutaId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ruta no encontrada"));

    Ciudad origen = ciudadRepository.findById(rutaCiudadDTO.getCiudadOrigenId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad de origen no encontrada"));

    Ciudad destino = ciudadRepository.findById(rutaCiudadDTO.getCiudadDestinoId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad de destino no encontrada"));

    ruta.setOrigen(origen);
    ruta.setDestino(destino);
    rutaRepository.save(ruta);
}

    public void borrarRuta(Long id) {
        rutaRepository.deleteById(id);        
    }
}
