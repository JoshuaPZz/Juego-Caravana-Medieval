package co.edu.javeriana.caravana_medieval.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.dto.ProductoDTO;
import co.edu.javeriana.caravana_medieval.mapper.ProductoMapper;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval.model.Producto;
import co.edu.javeriana.caravana_medieval.repository.CaravanaProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;

@Service
public class ComprarService {

    @Autowired
    private CaravanaProductoRepository caravanaProductoRepository;

    @Autowired 
    private CaravanaRepository caravanaRepository;

    @Autowired
    private CaravanaService caravanaService;
    
    @Autowired
    private CiudadProductoService ciudadProductoService;

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CiudadRepository ciudadRepository;

    public void comprarServicio(Long idCarvana, Long idServicio) {

    Caravana caravana = caravanaService.getCaravanaById(idCarvana);
    Ciudad ciudad = caravanaService.getCiudadActual(idCarvana);

        // Lógica para comprar un servicio en la ciudad
        // 1. Verificar si la caravana tiene suficiente dinero
        // 2. Verificar si el servicio está disponible en la ciudad
        // 3. Realizar la compra y actualizar el estado de la caravana y el servicio

        caravana = caravanaRepository.saveAndFlush(caravana);
    }

    public CaravanaProducto comprarProducto(Long idCaravana, Long idProducto) {
        Caravana caravana = caravanaService.getCaravanaById(idCaravana);
        Ciudad ciudad = caravanaService.getCiudadActual(idCaravana);
        Producto producto = ProductoMapper.toEntity(productoService.getProductoById(idProducto).get());
        if(caravana.getDineroDisponible() < 0) {
            throw new IllegalArgumentException("No tienes suficiente dinero para comprar el producto.");
        }
        if(caravana.getDineroDisponible() < ciudad.getProductos().get(idProducto.intValue()).getPrecioVenta()) {
            throw new IllegalArgumentException("No tienes suficiente dinero para comprar el producto.");
        }
        CaravanaProducto caravanaProducto = new CaravanaProducto();
        caravanaProducto.setProducto(producto);
        caravanaProducto.setCaravana(caravana);
        caravanaProducto.setCantidad(69);
        caravana.getProductos().add(caravanaProducto);
        caravana.setDineroDisponible((int) (caravana.getDineroDisponible() - ciudad.getProductos().get(idProducto.intValue()).getPrecioVenta()));
        ciudad.getProductos().removeIf(p -> p.getProducto().getId().equals(idProducto));
        caravana = caravanaRepository.saveAndFlush(caravana);
        ciudad= ciudadRepository.saveAndFlush(ciudad);
        return caravanaProductoRepository.saveAndFlush(caravanaProducto);
    }
}
