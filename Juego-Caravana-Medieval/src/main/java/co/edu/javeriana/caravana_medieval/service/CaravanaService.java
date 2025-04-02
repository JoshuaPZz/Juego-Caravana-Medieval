package co.edu.javeriana.caravana_medieval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.Ciudad;


@Service
public class CaravanaService {
    @Autowired
    private CaravanaRepository caravanaRepository;
    @Autowired
    private CiudadRepository ciudadRepository;

    public List<Caravana> getAllCaravanas() {
        return caravanaRepository.findAll();
    }
    
    public Caravana getCaravanaById(Long id) {
        return caravanaRepository.findById(id).orElse(null);
    }

    public Ciudad getCiudadActual(Long id) {
        Long ciudadId = caravanaRepository.findCiudadActualIdByCaravanaId(id);
        return ciudadRepository.findById(ciudadId).orElse(null);
    }

}
