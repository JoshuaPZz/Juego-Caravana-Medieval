package co.edu.javeriana.caravana_medieval.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval.model.Producto;

@Service
public class VenderService {

    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired
    private CaravanaRepository caravanaRepository;
    @Autowired
    private CiudadProductoRepository ciudadProductoRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public void vender(Long idCaravana, Long idCiudad, Long idProducto) {

        Ciudad ciudad = ciudadRepository.findById(idCiudad).orElse(null);
        Caravana caravana = caravanaRepository.findById(idCaravana).orElse(null);
        Producto producto = productoRepository.findById(idProducto).orElse(null);
        CiudadProducto ciudadProducto = ciudadProductoRepository.findById(idProducto).orElse(null);
        if (caravana != null && ciudadProducto != null) {
            int dinerocaravana = (int) (caravana.getDineroDisponible() + ciudadProducto.getPrecioVenta());
            caravana.setDineroDisponible(dinerocaravana);
        }
        if (ciudad != null) {
            List<CiudadProducto> productos = ciudad.getProductos();
            if (productos != null) {
                productos.add(ciudadProducto);
            }
        }
        ciudadProducto.setStock(ciudadProducto.getStock() + 1);
        ciudadProducto.setPrecioCompra(ciudadProducto.getPrecioCompra() * 1.1);

    }
    
}
