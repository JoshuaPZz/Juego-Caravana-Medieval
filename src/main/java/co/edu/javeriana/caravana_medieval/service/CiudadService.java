package co.edu.javeriana.caravana_medieval.service;

import java.util.List;
import java.util.Optional;

import co.edu.javeriana.caravana_medieval.dto.ServicioCompraDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadRutasDTO;
import co.edu.javeriana.caravana_medieval.mapper.CiudadMapper;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.model.Producto;
import co.edu.javeriana.caravana_medieval.model.Ruta;

import co.edu.javeriana.caravana_medieval.dto.CiudadServicioDTO;
import co.edu.javeriana.caravana_medieval.mapper.ServicioCompraMapper;
import co.edu.javeriana.caravana_medieval.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;

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
    public Optional<CiudadServicioDTO> getCiudadService(Long ciudadId) {
        Optional<Ciudad> ciudadOpt = ciudadRepository.findById(ciudadId);
        if(ciudadOpt.isEmpty()) {
            return Optional.empty();
        }
        Ciudad ciudad = ciudadOpt.get();
        List<CiudadServicio> relacionCiudadServicio = ciudad.getServicios();
        List<Long> idServicios = relacionCiudadServicio.stream()
                .map(CiudadServicio::getServicio)
                .map(Servicio::getId)
                .toList();
        CiudadServicioDTO ciudadServicioDTO = new CiudadServicioDTO(ciudadId, idServicios);
        return Optional.of(ciudadServicioDTO);
    }

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
