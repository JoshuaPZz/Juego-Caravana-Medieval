package co.edu.javeriana.caravana_medieval.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import co.edu.javeriana.caravana_medieval.dto.*;
import co.edu.javeriana.caravana_medieval.mapper.CiudadMapper;
import co.edu.javeriana.caravana_medieval.mapper.RutaMapper;
import co.edu.javeriana.caravana_medieval.model.*;
import co.edu.javeriana.caravana_medieval.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadRutaService {
    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private RutaService rutaService;

    @Autowired
    private CiudadService ciudadService;

    public Optional<CiudadRutasDTO> getCiudadRutas(Long ciudadId) {
        Optional<Ciudad> ciudadOpt = ciudadRepository.findById(ciudadId);
        if(ciudadOpt.isEmpty()){
            return Optional.empty();
        }
        Ciudad ciudad = ciudadOpt.get();
        List<Ruta> relacionRutaOrigen = ciudad.getRutasOrigen();
        List<Long> idRutasOrigen = relacionRutaOrigen.stream()
                            .map(Ruta::getId)
                            .toList();
        List<Ruta> relacionRutaDestino = ciudad.getRutasDestino();
        List<Long> idRutasDestino = relacionRutaDestino.stream()
                            .map(Ruta::getId)
                            .toList();                                                    
        CiudadRutasDTO ciudadRutasDTO = new CiudadRutasDTO(ciudadId, idRutasOrigen, idRutasDestino);
        return Optional.of(ciudadRutasDTO);
    }

    public Optional<List<CiudadDTO>> getDestinosCiudad (CiudadRutasDTO ciudadRutasDTO){
        List<RutaDTO> rutasOrigenDTO = rutaService.listaIdstoRuta(ciudadRutasDTO.getIdRutasOrigen())
                .stream()
                .toList();
        List<Long> idsRutas = rutasOrigenDTO.stream()
                .map(RutaDTO::getId)
                .toList();
        List<RutaCiudadDTO> rutaCiudadDTOs = idsRutas.stream()
                .map(rutaService::getRutaCiudad)
                .toList().stream()
                .map(Optional<RutaCiudadDTO>::get)
                .toList();
        List<Long> idCiudadesDestino = rutaCiudadDTOs.stream()
                .map(RutaCiudadDTO::getCiudadDestinoId)
                .toList();
        return Optional.of(idCiudadesDestino
                .stream()
                .map(ciudadService::getCiudadById)
                .toList()
                .stream()
                .map(Optional<CiudadDTO>::get)
                .toList());
    }


}
