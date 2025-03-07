package co.edu.javeriana.caravana_medieval.service;

import java.util.List;
import java.util.Optional;

import co.edu.javeriana.caravana_medieval.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadRutasDTO;
import co.edu.javeriana.caravana_medieval.mapper.CiudadMapper;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.model.Producto;
import co.edu.javeriana.caravana_medieval.model.Ruta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.model.CiudadProducto;

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

    public void guardarCiudad(CiudadDTO ciudadDTO) {
        Ciudad ciudad = CiudadMapper.toEntity(ciudadDTO);
        ciudadRepository.save(ciudad);

    }

    public Optional<CiudadProductoDTO> getCiudadProducto(Long ciudadId) {
        Optional<Ciudad> ciudadOpt = ciudadRepository.findById(ciudadId);
        if (ciudadOpt.isEmpty()) {
            return Optional.empty();
        }
        Ciudad ciudad = ciudadOpt.get();
        List<CiudadProducto> relacionCiudadProducto = ciudad.getProductos();
        List<Long> idProductos = relacionCiudadProducto.stream()
                .map(CiudadProducto::getProducto)
                .map(Producto::getId)
                .toList();
        CiudadProductoDTO ciudadProductoDTO = new CiudadProductoDTO(ciudadId, idProductos);
        return Optional.of(ciudadProductoDTO);
    }

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
        List<Ruta> relacionRutaDestino = ciudad.getRutasOrigen();
        List<Long> idRutasDestino = relacionRutaDestino.stream()
                            .map(Ruta::getId)
                            .toList();                                                    
        CiudadRutasDTO ciudadRutasDTO = new CiudadRutasDTO(ciudadId, idRutasOrigen, idRutasDestino);
        return Optional.of(ciudadRutasDTO);
    }

}
