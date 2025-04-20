package co.edu.javeriana.caravana_medieval.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.repository.CaravanaProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.ProductoDTO;
import co.edu.javeriana.caravana_medieval.mapper.CaravanaProductoMapper;
import co.edu.javeriana.caravana_medieval.mapper.ProductoMapper;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.model.Producto;

@Service
public class CaravanaService {
    @Autowired
    private CaravanaRepository caravanaRepository;
    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired
    private CaravanaProductoRepository caravanaProductoRepository;
    @Autowired
    private ProductoService productoService;

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

    public List<ProductoDTO> getCaravanaProductoById(Long id) {
        List<CaravanaProducto> caravanaProducto = caravanaProductoRepository.findByIdCaravana(id);
        List<ProductoDTO> productosDTO = new ArrayList<>();
        for (CaravanaProducto cp : caravanaProducto) {
            productosDTO.add(ProductoMapper.toDTO(cp.getProducto()));
        }
        return productosDTO;
    }

    public List<CaravanaProductoDTO> getCaravanaProductoDTOs(Long id) {
        return caravanaProductoRepository.findByIdCaravana(id)
                .stream()
                .map(CaravanaProductoMapper::toDTO)
                .toList();
    }
}
