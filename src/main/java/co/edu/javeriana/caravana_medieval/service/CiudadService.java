package co.edu.javeriana.caravana_medieval.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.edu.javeriana.caravana_medieval.dto.*;
import co.edu.javeriana.caravana_medieval.mapper.CiudadMapper;
import co.edu.javeriana.caravana_medieval.mapper.CiudadProductoMapper;
import co.edu.javeriana.caravana_medieval.mapper.ServicioCompraMapper;
import co.edu.javeriana.caravana_medieval.model.*;
import co.edu.javeriana.caravana_medieval.repository.CiudadProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.ServicioCompraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;

@Service
public class CiudadService {
    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CiudadProductoRepository ciudadProductoRepository;

    private Logger log = LoggerFactory.getLogger(getClass().getName());

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

    public Optional<List<CiudadProductoDTO>> getCiudadProducto(Long ciudadId) {
        Optional<Ciudad> ciudadOpt = ciudadRepository.findById(ciudadId);
        if (ciudadOpt.isEmpty()) {
            return Optional.empty();
        }
        Ciudad ciudad = ciudadOpt.get();
        List<CiudadProductoDTO> ciudadProductoDTOs = ciudad.getProductos().stream()
                .map(CiudadProductoMapper::toDTO)
                .toList();
        return Optional.of(ciudadProductoDTOs);
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

    public CiudadProductoDTO getCiudadProductoTupla(List<CiudadProductoDTO> ciudadProductosDTO, Long idCiudad, Long idProducto) {
        
        CiudadProductoDTO ciudadProducto = ciudadProductosDTO.stream()
        .filter(x -> x.getIdCiudad() == idCiudad && x.getIdProducto() == idProducto)
        .findFirst()
        .orElse(null);
        
        return ciudadProducto;
    }

/*

    public void updateCiudadProducto(CiudadProductoDTO ciudadProductoDTO) {
        Ciudad ciudad;
        System.out.println(ciudadProductoDTO);
        Optional<Ciudad> ciudadOpt = ciudadRepository.findById(ciudadProductoDTO.getIdCiudad());
        if(ciudadOpt.isEmpty()) {
            return;
        }
        ciudad = ciudadOpt.get();
        List<Producto> selectedProductos = productoRepository.findAllById(ciudadProductoDTO.getIdProductos());
        ciudadProductoRepository.deleteByCiudadId(ciudad.getId());
        for(Producto p : selectedProductos) {
            CiudadProducto ciudadProducto = new CiudadProducto(ciudad, p, 0, 0);
            ciudadProducto.setPrecioVenta(0);
            ciudadProducto.setPrecioCompra(0);
            ciudadProductoRepository.save(ciudadProducto);
        }
    }
         */
}
