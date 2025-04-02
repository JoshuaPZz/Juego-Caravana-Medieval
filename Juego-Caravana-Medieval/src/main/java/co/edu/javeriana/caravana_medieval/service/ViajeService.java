package co.edu.javeriana.caravana_medieval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadRutasDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaCiudadDTO;
import co.edu.javeriana.caravana_medieval.mapper.CiudadMapper;
import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.RutaRepository;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.Ciudad;

@Service
public class ViajeService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private CaravanaService caravanaService;

    public CiudadDTO getCiudadActual(Long id) {
        Ciudad ciudad = caravanaService.getCiudadActual(id);
        CiudadMapper ciudadMapper = new CiudadMapper();
        CiudadDTO ciudadDTO = ciudadMapper.toDTO(ciudad);
        return ciudadDTO;
    }




    
}
