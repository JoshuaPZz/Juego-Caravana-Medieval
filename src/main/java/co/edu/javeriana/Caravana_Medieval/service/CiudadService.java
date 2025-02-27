package co.edu.javeriana.caravana_medieval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.model.Ciudad;

@Service
public class CiudadService {
    @Autowired
    private CiudadRepository ciudadRepository;

    public List<Ciudad> getAllCiudades() {
        return ciudadRepository.findAll();
    }

    public Ciudad getCiudadById(Long id) {
        return ciudadRepository.findById(id).orElse(null);
    }
    
}
