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
public class CiudadService {
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
        return ciudadProductosDTO.stream()
        .filter(x -> x.getIdCiudad() == idCiudad && x.getIdProducto() == idProducto).
                findFirst()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public CiudadServicioDTO getCiudadServicioTupla(List<CiudadServicioDTO> ciudadServiciosDTO, Long idCiudad, Long idServicio) {
        return ciudadServiciosDTO.stream()
        .filter(x -> x.getIdCiudad() == idCiudad && x.getIdServicio() == idServicio).
                findFirst()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public CiudadProducto crearCiudadProducto(CiudadProductoDTO ciudadProductoDTO) {
        ciudadProductoDTO.setId(null);
        CiudadProducto ciudadProducto = CiudadProductoMapper.toEntity(ciudadProductoDTO);
        ciudadProducto.setCiudad(ciudadRepository.findById(ciudadProductoDTO.getIdCiudad()).get());
        log.info(ciudadProducto.getCiudad().getNombre());
        ciudadProducto.setProducto(productoRepository.findById(ciudadProductoDTO.getIdProducto()).get());
        log.info(ciudadProducto.getProducto().getNombre());
        return ciudadProductoRepository.save(ciudadProducto);
    }
    public void actualizarCiudadProducto(CiudadProductoDTO ciudadProductoDTO) {
        CiudadProducto ciudadProducto = CiudadProductoMapper.toEntity(ciudadProductoDTO);
        ciudadProducto.setCiudad(ciudadRepository.findById(ciudadProductoDTO.getIdCiudad()).get());
        ciudadProducto.setProducto(productoRepository.findById(ciudadProductoDTO.getIdProducto()).get());
        ciudadProductoRepository.save(ciudadProducto);
    }

    public void saveEditServicioCiudad(CiudadServicioDTO ciudadServicioDTO) {
        CiudadServicio ciudadServicio = CiudadServicioMapper.toEntity(ciudadServicioDTO);
        ciudadServicio.setServicio(servicioRepository.findById(ciudadServicioDTO.getIdServicio()).get());
        ciudadServicio.setCiudad(ciudadRepository.findById(ciudadServicioDTO.getIdCiudad()).get());
        ciudadServicioRepository.save(ciudadServicio);
    }

    public void borrarCiudad(Long id) {
        ciudadRepository.deleteById(id);
    }
    
   public void borrarAllCiudadServicio(Long idCiudad) {
        ciudadRepository.deleteCiudadCascada(idCiudad);
    }


    public void borrarCiudadServicio(Long idCiudad, Long idServicio) {
        List<CiudadServicioDTO> ciudadServicioDTOs = getCiudadService(idCiudad).get();
        CiudadServicioDTO ciudadServicioDTO = ciudadServicioDTOs.stream()
                .filter(x -> x.getIdCiudad().equals(idCiudad) && x.getIdServicio().equals(idServicio))
                .findFirst()
                .orElse(null);
        if (ciudadServicioDTO != null) {
            log.error("ID DE CIUDAD: "+ciudadServicioDTO.getIdCiudad());
            log.error("ID DE SERVICIO: "+ciudadServicioDTO.getIdServicio());
            log.error("ID DE LA RELACION: "+ciudadServicioDTO.getId());
            Long id = ciudadServicioDTO.getId();
            servicioCompraRepository.deleteAll();
            ciudadServicioRepository.deleteById(id);
        } else {
            log.error("es nulo");
        }
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
