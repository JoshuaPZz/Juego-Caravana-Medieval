package co.edu.javeriana.caravana_medieval.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.edu.javeriana.caravana_medieval.mapper.ProductoMapper;
import co.edu.javeriana.caravana_medieval.model.Producto;
import co.edu.javeriana.caravana_medieval.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.javeriana.caravana_medieval.dto.ProductoDTO;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findAll().stream()
                .map(ProductoMapper::toDTO).toList();
    }

    public Optional<ProductoDTO> getProductoById(Long id) {
        return productoRepository.findById(id)
                .map(ProductoMapper::toDTO);
    }

    public void guardarProducto(ProductoDTO productoDTO) {
        Producto producto = ProductoMapper.toEntity(productoDTO);
        productoRepository.save(producto);

    }
    public List<ProductoDTO> listaIdsToProducto (List<Long> productosId) {
        List<ProductoDTO> productosDTO = new ArrayList<ProductoDTO>();
        for(Long id : productosId) {
            productosDTO.add(getProductoById(id).get());
        }
        return productosDTO;
    }
}
