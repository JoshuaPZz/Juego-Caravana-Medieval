package co.edu.javeriana.caravana_medieval.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Optional;

import co.edu.javeriana.caravana_medieval.dto.*;
import co.edu.javeriana.caravana_medieval.mapper.CiudadMapper;
import co.edu.javeriana.caravana_medieval.mapper.CiudadProductoMapper;
import co.edu.javeriana.caravana_medieval.mapper.CiudadServicioMapper;
import co.edu.javeriana.caravana_medieval.mapper.ServicioCompraMapper;
import co.edu.javeriana.caravana_medieval.model.*;
import co.edu.javeriana.caravana_medieval.repository.*;
import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadRutaService {
    @Autowired
    private CiudadRepository ciudadRepository;

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


}
