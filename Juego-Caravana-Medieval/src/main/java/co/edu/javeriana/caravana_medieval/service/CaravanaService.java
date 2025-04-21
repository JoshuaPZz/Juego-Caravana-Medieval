package co.edu.javeriana.caravana_medieval.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.repository.CaravanaProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.ProductoDTO;
import co.edu.javeriana.caravana_medieval.mapper.CaravanaMapper;
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

    public CaravanaDTO reiniciarCaravana(){
        // ESTA FUNCION ES PROVICIONAL MIENTRAS NO TENEMOS AUTENTICACION DE USUARIOS
        // POR ESO SOLO JUGAMOS EN LA CARAVANA 
        Caravana caravana = getCaravanaById(Long.valueOf(1));
        caravana.setCapacidadMax(100);
        caravana.setDineroDisponible(600);
        caravana.setHoraViaje(java.time.LocalTime.of(8, 0));
        caravana.setPuntosVida(100);
        caravana.setTieneGuardias(false);
        caravana.setVelocidad(10);
        caravana.setTiempoTranscurrido(Duration.ofSeconds(0));
        CaravanaDTO caravanaDTO = CaravanaMapper.toDTO(caravanaRepository.save(caravana));
        return caravanaDTO;
    }
}
