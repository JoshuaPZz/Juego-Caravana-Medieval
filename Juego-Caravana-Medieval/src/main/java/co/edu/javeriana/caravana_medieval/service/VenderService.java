package co.edu.javeriana.caravana_medieval.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.CaravanaProducto;
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
    //@Autowired
    //private CaravanaProductoRepository caravanaProductoRepository;

    public void vender(Long idCaravana, Long idProducto, Long cantidad) {
        Caravana caravana = caravanaRepository.findById(idCaravana)
            .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

        Ciudad ciudad = ciudadRepository.findById(caravana.getCiudadActual().getId())
            .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        Producto producto = productoRepository.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        
        // CaravanaProducto caravanaProducto = caravanaProductoRepository
          //  .findByCaravanaIdAndProductoId(idCaravana, idProducto)
         //   .orElseThrow(() -> new RuntimeException("La caravana no tiene este producto"));

       // if (caravanaProducto.getCantidad() < cantidad) {
        //    throw new RuntimeException("La caravana no tiene suficiente cantidad del producto para vender");
        //}

        CiudadProducto ciudadProducto = ciudadProductoRepository
            .findByCiudadIdAndProductoId(ciudad.getId(), idProducto)
            .orElse(null);

        // if(cantidad>caravanaProducto.getCantidad()){
         //   throw new RuntimeException("No hay suficiente cantidad del producto en la ciudad para vender");
        //}


        double precioCompra;

        if (ciudadProducto != null) {
            // El producto ya existe en la ciudad
            ciudadProducto.setStock(ciudadProducto.getStock() + cantidad.intValue());
            precioCompra = ciudadProducto.calcularPrecioCompra();
            ciudadProducto.setPrecioCompra(precioCompra);
            ciudadProducto.setPrecioVenta(ciudadProducto.calcularPrecioVenta());
            ciudadProductoRepository.save(ciudadProducto);
        } else {
            // El producto no existe en la ciudad
            ciudadProducto = new CiudadProducto();
            ciudadProducto.setCiudad(ciudad);
            ciudadProducto.setProducto(producto);
            ciudadProducto.setStock(cantidad.intValue());
            ciudadProducto.setFactorDemanda(1.0); 
            ciudadProducto.setFactorOferta(0.8);
            precioCompra = ciudadProducto.calcularPrecioCompra();
            ciudadProducto.setPrecioCompra(precioCompra);
            ciudadProducto.setPrecioVenta(ciudadProducto.calcularPrecioVenta());
            ciudadProductoRepository.save(ciudadProducto);
        }

       // int nuevaCantidad = caravanaProducto.getCantidad() - cantidad.intValue();
       // caravanaProducto.setCantidad(nuevaCantidad);
        
       // if (nuevaCantidad <= 0) {
          //  caravana.getProductos().remove(caravanaProducto);
           // caravanaProductoRepository.delete(caravanaProducto);
      //  } else {
            //caravanaProductoRepository.save(caravanaProducto);
        //}

        double dineroGanado = precioCompra * cantidad;
        caravana.setDineroDisponible(caravana.getDineroDisponible() + dineroGanado);
        
        // Guardar los cambios en la caravana
        caravanaRepository.save(caravana);
    }
}