package co.edu.javeriana.caravana_medieval.service;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.javeriana.caravana_medieval.dto.CiudadServicioDTO;
import co.edu.javeriana.caravana_medieval.mapper.ServicioMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import co.edu.javeriana.caravana_medieval.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval.mapper.CiudadProductoMapper;
import co.edu.javeriana.caravana_medieval.mapper.ProductoMapper;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.model.Servicio;
import co.edu.javeriana.caravana_medieval.model.ServicioCompra;
import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.repository.ServicioCompraRepository;
import co.edu.javeriana.caravana_medieval.model.Producto;
import co.edu.javeriana.caravana_medieval.repository.CaravanaProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadProductoRepository;

@Service
public class ComprarService {

    @Autowired
    private CaravanaProductoRepository caravanaProductoRepository;

    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private ServicioCompraRepository servicioCompraRepository;
    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired
    private CaravanaService caravanaService;
    @Autowired
    private CiudadServicioService ciudadServicioService;
    @Autowired
    private ServicioService servicioService;
    @Autowired
    private CiudadProductoService ciudadProductoService;
    @Autowired
    private CiudadProductoRepository ciudadProductoRepository;
    @Autowired
    private ProductoService productoService;

    private Logger log = LoggerFactory.getLogger(getClass());

    public void comprarServicio(Long idCarvana, Long idServicio) {

        Caravana caravana = caravanaService.getCaravanaById(idCarvana);
        Ciudad ciudad = caravanaService.getCiudadActual(idCarvana);
        Servicio servicio = ServicioMapper.toEntity(servicioService.getServicioById(idServicio).get());
        List<CiudadServicioDTO> ciudadServicioDTO = ciudadServicioService.getCiudadService(ciudad.getId()).get();
        CiudadServicioDTO ciudadServicioComprar = ciudadServicioDTO.stream()
                .filter(x -> x.getIdServicio() == servicio.getId()).findFirst().orElse(null);
        System.out.println("Servicio a comprar: " + ciudadServicioComprar.getNombreServicio());
        System.out.println("En la ciudad " + ciudadServicioComprar.getIdCiudad());

        // 1. Verificar que la caravana tenga suficiente dinero para comprar el servicio
        if (caravana.getDineroDisponible() - ciudadServicioComprar.getPrecio() <= 0) {
            throw new IllegalArgumentException(
                    "La caravana no cuenta con suficiente dinero para comprar este servicio. Dinero Necesario: "
                            + ciudadServicioComprar.getPrecio());
        }

        // 2. Verificar si el servicio está disponible en la ciudad
        ServicioCompra servicioCompraAux = ciudad.getCompras().stream()
                .filter(x -> x.getCaravana().getId() == caravana.getId() && x.getServicio().getId() == servicio.getId())
                .findFirst()
                .orElse(null);
        System.out.println("/////");

        // 3. Realizar la compra y actualizar el estado de la caravana y el servicio

        if (servicio.getNombre() == "Reparar") {
            if (caravana.getPuntosVida() != 100) {
                caravana.setPuntosVida(100);
            } else {
                throw new IllegalArgumentException(
                        "La caravana ya tiene los puntos de vida al máximo.");
            }
        } else if (servicio.getNombre() == "Mejorar capacidad") {
            if (caravana.getCapacidadMax() < 400) {
                if ((caravana.getCapacidadMax() + 20) <= 400) {
                    caravana.setCapacidadMax(caravana.getCapacidadMax() + 20);
                } else if ((caravana.getCapacidadMax() + 20) > 400) {
                    caravana.setCapacidadMax(400);
                }
            } else {
                throw new IllegalArgumentException(
                        "La caravana ya tiene la capacidad al máximo (400).");
            }
        } else if (servicio.getNombre() == "Mejorar velocidad") {
            if (caravana.getVelocidad() < 15) {
                caravana.setVelocidad(caravana.getVelocidad() + 1);
            } else {
                throw new IllegalArgumentException(
                        "La caravana ya alcanzó su máximo de velocidad (15).");
            }
        } else if (servicio.getNombre() == "Guardias") {
            if (caravana.isTieneGuardias()) {
                throw new IllegalArgumentException(
                        "La caravana ya tiene guardias, este servicio no es acumulable.");
            } else {
                caravana.setTieneGuardias(true);
            }
        }

        if (servicioCompraAux != null) {
            System.out.println(
                    servicioCompraAux.getServicio().getNombre() + " /// " + servicioCompraAux.getCiudad().getId());
            throw new IllegalArgumentException(
                    "La caravana ya compró este servicio en esta ciudad.");
        }

        caravana.setDineroDisponible(caravana.getDineroDisponible() - ciudadServicioComprar.getPrecio());

        ServicioCompra servicioCompra = new ServicioCompra(caravana, ciudad, servicio);
        ciudad.getCompras().add(servicioCompra);
        caravana.getCompras().add(servicioCompra);

        // Guardar en los repositorios
        caravanaRepository.save(caravana);
        ciudadRepository.save(ciudad);
        servicioCompraRepository.save(servicioCompra);
    }

    public CaravanaProducto comprarProducto(CaravanaProductoDTO caravanaProductoDTO, Long idCaravana) {
        Caravana caravana = caravanaService.getCaravanaById(idCaravana);
        if (caravana.getDineroDisponible() < 0) {
            throw new IllegalArgumentException("No tienes suficiente dinero para comprar el producto.");
        }
        Ciudad ciudad = caravanaService.getCiudadActual(idCaravana);
        Producto producto = ProductoMapper
                .toEntity(productoService.getProductoById(caravanaProductoDTO.getIdProducto()).get());
        List<CiudadProductoDTO> ciudadProductoList = ciudadProductoService.getCiudadProducto(ciudad.getId()).get();
        CiudadProductoDTO ciudadProductoDTO = ciudadProductoService.getCiudadProductoTupla(ciudadProductoList,
                ciudad.getId(), producto.getId());
        if (caravana.getDineroDisponible() < ciudadProductoDTO.getPrecioCompra()) {
            throw new IllegalArgumentException("No tienes suficiente dinero para comprar el producto.");
        }
        if (caravanaProductoDTO.getCantidad() > ciudadProductoDTO.getStock()) {
            throw new IllegalArgumentException("No puedes comprar mas cantidad del stock disponible");
        }
        int capacidadAct = caravana.getProductos()
                .stream()
                .mapToInt(CaravanaProducto::getCantidad)
                .sum();
        if (capacidadAct + caravanaProductoDTO.getCantidad() > caravana.getCapacidadMax()) {
            throw new IllegalArgumentException("No tienes suficiente capacidad para comprar el producto");
        }
        if (caravanaProductoDTO.getCantidad() < 0) {
            throw new IllegalArgumentException("No puedes comprar cantidades negativas");
        }

        Optional<CaravanaProducto> optCaravanaProducto = caravanaProductoRepository.findByIdProducto(producto.getId());
        CaravanaProducto caravanaProducto = optCaravanaProducto.orElseGet(() -> {
            CaravanaProducto nuevo = new CaravanaProducto();
            nuevo.setProducto(producto);
            nuevo.setCaravana(caravana);
            return nuevo;
        });
        caravanaProducto.setCantidad(caravanaProducto.getCantidad() + caravanaProductoDTO.getCantidad());
        caravana.getProductos().add(caravanaProducto);
        caravana.setDineroDisponible((int) (caravana.getDineroDisponible() - ciudadProductoDTO.getPrecioCompra()));
        ciudadProductoDTO.setStock(ciudadProductoDTO.getStock() - caravanaProductoDTO.getCantidad());
        if (ciudadProductoDTO.getStock() == 0) {
            ciudadProductoRepository.delete(ciudadProductoRepository.getReferenceById(ciudadProductoDTO.getId()));
        } else {
            ciudadProductoService.updateCiudadProducto(ciudadProductoDTO);
        }
        return caravanaProductoRepository.save(caravanaProducto);
    }
}
