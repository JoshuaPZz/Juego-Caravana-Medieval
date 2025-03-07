package co.edu.javeriana.caravana_medieval.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.dto.RutaCiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval.mapper.RutaMapper;
import co.edu.javeriana.caravana_medieval.model.Ruta;
import co.edu.javeriana.caravana_medieval.repository.RutaRepository;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    public List<RutaDTO> listarRutas() {
        return rutaRepository.findAll().stream()
            .map(RutaMapper::toDto).toList();
    }

    public Optional<RutaDTO> buscarRuta(Long id){
        return rutaRepository.findById(id)
        .map(RutaMapper::toDto);
    }

    public Optional <RutaCiudadDTO> getOrigenRuta (Long rutaId) {
        Optional <Ruta> rutaOpt = rutaRepository.findById(rutaId);
        if(rutaOpt.isEmpty()){
            return Optional.empty();
        }
        Ruta ruta = rutaOpt.get();
        ruta.getOrigen();
        Long origenId = ruta.getOrigen().getId();
        Long destinoId = ruta.getDestino().getId();

        RutaCiudadDTO rutaCiudadDTO = new RutaCiudadDTO(rutaId, origenId, destinoId);

        return Optional.of(rutaCiudadDTO);
    }
}
