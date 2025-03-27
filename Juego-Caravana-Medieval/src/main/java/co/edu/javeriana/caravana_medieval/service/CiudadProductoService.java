package co.edu.javeriana.caravana_medieval.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.javeriana.caravana_medieval.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval.mapper.CiudadProductoMapper;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval.repository.CiudadProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.repository.ProductoRepository;

@Service
public class CiudadProductoService {

    @Autowired
    CiudadProductoRepository ciudadProductoRepository;

    @Autowired
    CiudadRepository ciudadRepository;

    @Autowired
    ProductoRepository productoRepository;
    
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

    public CiudadProductoDTO getCiudadProductoTupla(List<CiudadProductoDTO> ciudadProductosDTO, Long idCiudad, Long idProducto) {
        return ciudadProductosDTO.stream()
        .filter(x -> x.getIdCiudad() == idCiudad && x.getIdProducto() == idProducto).
                findFirst()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public CiudadProducto createCiudadProducto(CiudadProductoDTO ciudadProductoDTO) {
        ciudadProductoDTO.setId(null);
        CiudadProducto ciudadProducto = CiudadProductoMapper.toEntity(ciudadProductoDTO);
        ciudadProducto.setCiudad(ciudadRepository.findById(ciudadProductoDTO.getIdCiudad()).get());
        ciudadProducto.setProducto(productoRepository.findById(ciudadProductoDTO.getIdProducto()).get());
        return ciudadProductoRepository.save(ciudadProducto);
    }
    public CiudadProducto updateCiudadProducto(CiudadProductoDTO ciudadProductoDTO) {
        CiudadProducto ciudadProducto = CiudadProductoMapper.toEntity(ciudadProductoDTO);
        ciudadProducto.setCiudad(ciudadRepository.findById(ciudadProductoDTO.getIdCiudad()).get());
        ciudadProducto.setProducto(productoRepository.findById(ciudadProductoDTO.getIdProducto()).get());
        return ciudadProductoRepository.save(ciudadProducto);
    }
    
    public void deleteCiudadProducto(Long idCiudadProducto) {
        ciudadProductoRepository.deleteById(idCiudadProducto);
    }

}

