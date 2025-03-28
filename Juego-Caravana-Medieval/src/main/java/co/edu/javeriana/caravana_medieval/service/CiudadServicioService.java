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
public class CiudadServicioService {
    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CiudadProductoRepository ciudadProductoRepository;

    private Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private RutaRepository rutaRepository;
    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private CiudadServicioRepository ciudadServicioRepository;
    @Autowired
    private ServicioCompraRepository servicioCompraRepository;

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
