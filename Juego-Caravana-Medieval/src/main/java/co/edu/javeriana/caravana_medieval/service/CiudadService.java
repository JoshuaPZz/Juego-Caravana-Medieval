package co.edu.javeriana.caravana_medieval.service;

import java.util.List;
import java.util.Optional;

import co.edu.javeriana.caravana_medieval.dto.*;
import co.edu.javeriana.caravana_medieval.mapper.CiudadMapper;
import co.edu.javeriana.caravana_medieval.model.*;
import co.edu.javeriana.caravana_medieval.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadService {
    @Autowired
    private CiudadRepository ciudadRepository;

    public List<CiudadDTO> getAllCiudades() {
        return ciudadRepository.findAll().stream()
                .map(CiudadMapper::toDTO).toList();
    }

    public Optional<CiudadDTO> getCiudadById(Long id) {
        return ciudadRepository.findById(id)
                .map(CiudadMapper::toDTO);
    }

    public CiudadDTO createCiudad(CiudadDTO ciudadDTO) {
        ciudadDTO.setId(null);
        Ciudad ciudad = CiudadMapper.toEntity(ciudadDTO);
        return CiudadMapper.toDTO( ciudadRepository.save(ciudad));
    }
    
    public CiudadDTO editCiudad(CiudadDTO ciudadDTO) {
        if(ciudadDTO.getId()!=null){
        Ciudad ciudad = CiudadMapper.toEntity(ciudadDTO);
        ciudadRepository.save(ciudad);
        return CiudadMapper.toDTO( ciudad);
        }
        else {
            return null;
        }
    }

    public void borrarCiudad(Long id) {
        ciudadRepository.deleteById(id);
    }
    
    public void borrarAllCiudadServicio(Long idCiudad) {
        ciudadRepository.deleteCiudadCascada(idCiudad);
    }

}
