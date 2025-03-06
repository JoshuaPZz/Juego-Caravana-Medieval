package co.edu.javeriana.caravana_medieval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.mapper.CiudadMapper;
import co.edu.javeriana.caravana_medieval.repository.RutaRepository;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    public List<RutasDTO> getAllCiudades() {
        return rutaRepository.findAll().stream()
                .map(CiudadMapper::toDTO).toList();
    }
    
}
