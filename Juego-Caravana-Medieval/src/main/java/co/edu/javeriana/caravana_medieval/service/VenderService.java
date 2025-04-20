package co.edu.javeriana.caravana_medieval.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.repository.CaravanaProductoRepository;
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
    @Autowired
    private CaravanaProductoRepository caravanaProductoRepository;

    public void vender(Long idCaravana, Long idProducto, int cantidad) {
        Caravana caravana = caravanaRepository.findById(idCaravana)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

        Ciudad ciudad = ciudadRepository.findById(caravana.getCiudadActual().getId())
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        CaravanaProducto caravanaProducto = caravanaProductoRepository
                .findByCaravanaIdAndProductoId(idCaravana, idProducto)
                .orElseThrow(() -> new RuntimeException("La caravana no tiene este producto"));

        if (caravanaProducto.getCantidad() < cantidad) {
            throw new RuntimeException("La caravana no tiene suficiente cantidad del producto para vender");
        }

        CiudadProducto ciudadProducto = ciudadProductoRepository
                .findByCiudadIdAndProductoId(ciudad.getId(), idProducto)
                .orElse(null);

        if (cantidad < 0){
            throw new RuntimeException("No se pueden vender cantidades negativas");
        }

        double precioVenta;

        if (ciudadProducto != null) {
            // El producto ya existe en la ciudad
            ciudadProducto.setStock(ciudadProducto.getStock() + cantidad);
            ciudadProducto.setPrecioCompra(ciudadProducto.calcularPrecioCompra());
            precioVenta = ciudadProducto.calcularPrecioVenta();
            ciudadProducto.setPrecioVenta(precioVenta);
            ciudadProductoRepository.save(ciudadProducto);
        } else {
            // El producto no existe en la ciudad
            ciudadProducto = new CiudadProducto();
            ciudadProducto.setCiudad(ciudad);
            ciudadProducto.setProducto(producto);
            ciudadProducto.setStock(cantidad);
            ciudadProducto.setFactorDemanda(1.0);
            ciudadProducto.setFactorOferta(0.8);
            ciudadProducto.setPrecioCompra(ciudadProducto.calcularPrecioCompra());
            precioVenta = ciudadProducto.calcularPrecioVenta();
            ciudadProducto.setPrecioVenta(precioVenta);
            ciudadProductoRepository.save(ciudadProducto);
        }

        caravanaProducto.setCantidad(caravanaProducto.getCantidad() - cantidad);

        if (caravanaProducto.getCantidad() == 0) {
            caravana.getProductos().remove(caravanaProducto);
            caravanaProductoRepository.delete(caravanaProducto);
        } else {
            caravanaProductoRepository.save(caravanaProducto);
        }

        double dineroGanado = precioVenta * cantidad;
        caravana.setDineroDisponible(caravana.getDineroDisponible() + dineroGanado);

        // Guardar los cambios en la caravana
        caravanaRepository.save(caravana);
    }
}