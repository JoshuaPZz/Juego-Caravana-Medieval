package co.edu.javeriana.caravana_medieval.service;

import java.util.List;
import java.util.Optional;

import co.edu.javeriana.caravana_medieval.dto.*;
import co.edu.javeriana.caravana_medieval.mapper.CiudadServicioMapper;
import co.edu.javeriana.caravana_medieval.model.*;
import co.edu.javeriana.caravana_medieval.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadServicioService {
    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private CiudadServicioRepository ciudadServicioRepository;

    public Optional<List<CiudadServicioDTO>> getCiudadService(Long ciudadId) {
        Optional<Ciudad> ciudadOpt = ciudadRepository.findById(ciudadId);
        if(ciudadOpt.isEmpty()) {
            return Optional.empty();
        }
        Ciudad ciudad = ciudadOpt.get();
        List<CiudadServicioDTO> ciudadServiciosDTO = ciudad.getServicios().stream()
                .map(CiudadServicioMapper::toDTO)
                .toList();
        return Optional.of(ciudadServiciosDTO);
    }

    public CiudadServicioDTO getCiudadServicioTupla(List<CiudadServicioDTO> ciudadServiciosDTO, Long idCiudadServicio) {
        return ciudadServiciosDTO.stream()
        .filter(x -> x.getId() == idCiudadServicio).
                findFirst()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public CiudadServicio createCiudadServicio(CiudadServicioDTO ciudadServicioDTO) {
        ciudadServicioDTO.setId(null);
        CiudadServicio ciudadServicio = CiudadServicioMapper.toEntity(ciudadServicioDTO);
        ciudadServicio.setCiudad(ciudadRepository.findById(ciudadServicioDTO.getIdCiudad()).get());
        ciudadServicio.setServicio(servicioRepository.findById(ciudadServicioDTO.getIdServicio()).get());
        return ciudadServicioRepository.save(ciudadServicio);
    }

    public CiudadServicio updateCiudadServicio(CiudadServicioDTO ciudadServicioDTO) {
        CiudadServicio ciudadServicio = CiudadServicioMapper.toEntity(ciudadServicioDTO);
        ciudadServicio.setCiudad(ciudadRepository.findById(ciudadServicioDTO.getIdCiudad()).get());
        ciudadServicio.setServicio(servicioRepository.findById(ciudadServicioDTO.getIdServicio()).get());
        return ciudadServicioRepository.save(ciudadServicio);
    }
    
    public void deleteCiudadServicio(Long idCiudadServicio) {
        ciudadServicioRepository.deleteById(idCiudadServicio);       
    }
    
}
