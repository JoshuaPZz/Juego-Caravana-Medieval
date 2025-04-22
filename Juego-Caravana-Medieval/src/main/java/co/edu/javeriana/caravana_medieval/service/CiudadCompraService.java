package co.edu.javeriana.caravana_medieval.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.dto.ServicioCompraDTO;
import co.edu.javeriana.caravana_medieval.mapper.ServicioCompraMapper;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;

@Service
public class CiudadCompraService {
    @Autowired
    CiudadRepository ciudadRepository;

    public Optional<List<ServicioCompraDTO>> getCiudadCompras(Long ciudadId) {
    Optional<Ciudad> ciudadOpt = ciudadRepository.findById(ciudadId);
    if(ciudadOpt.isEmpty()) {
        return Optional.empty();
    }
    Ciudad ciudad = ciudadOpt.get();
    List<ServicioCompraDTO> servicioCompras = ciudad.getCompras().stream().map(ServicioCompraMapper::toDTO).toList();
    return Optional.of(servicioCompras);
    }
}