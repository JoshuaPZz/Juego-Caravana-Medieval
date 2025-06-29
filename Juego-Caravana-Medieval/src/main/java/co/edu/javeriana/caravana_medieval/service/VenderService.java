package co.edu.javeriana.caravana_medieval.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval.dto.CiudadProductoDTO;
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
    @Autowired
    private CaravanaService caravanaService;
    @Autowired
    private CiudadService ciudadService;

    public CiudadProducto venderProducto(CiudadProductoDTO ciudadProductoDTO, Long idCaravana) {
        Caravana caravana = caravanaService.getCaravanaById(idCaravana);

        Producto producto = productoRepository.findById(ciudadProductoDTO.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        CaravanaProducto caravanaProducto = caravanaProductoRepository
                .findByCaravanaIdAndProductoId(idCaravana, ciudadProductoDTO.getIdProducto())
                .orElseThrow(() -> new RuntimeException("La caravana no tiene este producto"));

        Ciudad ciudad = caravana.getCiudadActual();

        CiudadProducto ciudadProducto = ciudadProductoRepository
                .findByCiudadIdAndProductoId(ciudad.getId(), ciudadProductoDTO.getIdProducto())
                .orElse(null);

        if (caravanaProducto.getCantidad() < ciudadProductoDTO.getStock()) {
            throw new IllegalArgumentException("La caravana no tiene la cantidad suficiente de productos para vender.");
        }

        if (ciudadProductoDTO.getStock() < 0) {
            throw new RuntimeException("No se pueden vender cantidades negativas");
        }

        double precioVentaCalculado;

        if (ciudadProducto != null) {
            // El producto ya existe en la ciudad
            ciudadProducto.setStock(ciudadProducto.getStock() + ciudadProductoDTO.getStock());

            double nuevoPrecioCompra = ciudadProducto.calcularPrecioCompra();
            double nuevoPrecioVenta = ciudadProducto.calcularPrecioVenta();

            nuevoPrecioCompra = Math.max(5.0, nuevoPrecioCompra);
            nuevoPrecioVenta = Math.max(5.0, nuevoPrecioVenta);

            nuevoPrecioCompra = (double) (long) nuevoPrecioCompra;
            nuevoPrecioVenta = (double) (long) nuevoPrecioVenta;

            ciudadProducto.setPrecioCompra(nuevoPrecioCompra);
            ciudadProducto.setPrecioVenta(nuevoPrecioVenta);

            precioVentaCalculado = nuevoPrecioVenta;
        } else {
            // El producto no existe en la ciudad
            ciudadProducto = new CiudadProducto();
            ciudadProducto.setCiudad(ciudad);
            ciudadProducto.setProducto(producto);
            ciudadProducto.setStock(ciudadProductoDTO.getStock());

            ciudadProducto.setFactorDemanda(1.0);
            ciudadProducto.setFactorOferta(0.8);

            double precioCompraInicial = ciudadProducto.calcularPrecioCompra();
            double precioVentaInicial = ciudadProducto.calcularPrecioVenta();

            precioCompraInicial = Math.max(5.0, precioCompraInicial);
            precioVentaInicial = Math.max(5.0, precioVentaInicial);

            precioCompraInicial = (double) (long) precioCompraInicial;
            precioVentaInicial = (double) (long) precioVentaInicial;

            ciudadProducto.setPrecioCompra(precioCompraInicial);
            ciudadProducto.setPrecioVenta(precioVentaInicial);

            precioVentaCalculado = precioVentaInicial;
        }

        caravanaProducto.setCantidad(caravanaProducto.getCantidad() - ciudadProductoDTO.getStock());

        if (caravanaProducto.getCantidad() == 0) {
            caravana.getProductos().remove(caravanaProducto);
            caravanaProductoRepository.delete(caravanaProducto);
        } else {
            caravanaProductoRepository.save(caravanaProducto);
        }

        double dineroGanado = precioVentaCalculado * ciudadProductoDTO.getStock();
        caravana.setDineroDisponible(caravana.getDineroDisponible() + dineroGanado);
        // caravana.setCapacidadMax(caravana.getCapacidadMax() +
        // ciudadProductoDTO.getStock());

        // Guardar los cambios en la caravana
        caravanaRepository.save(caravana);

        // TO DO
        return ciudadProductoRepository.save(ciudadProducto);
    }
}