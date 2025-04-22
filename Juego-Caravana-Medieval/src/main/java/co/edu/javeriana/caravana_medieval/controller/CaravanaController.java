package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.ProductoDTO;
import co.edu.javeriana.caravana_medieval.mapper.CaravanaMapper;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.service.CaravanaService;

@RestController
@RequestMapping("/caravana")
public class CaravanaController {
    @Autowired
    private CaravanaService caravanaService;

    @GetMapping("/{id}")
    public CaravanaDTO getCaravanaById(@PathVariable Long id) {
        Caravana caravana = caravanaService.getCaravanaById(id);
        CaravanaDTO caravanaDTO = CaravanaMapper.toDTO(caravana);
        return caravanaDTO;
    }

    @GetMapping("/{id}/productos")
    public List<ProductoDTO> getCaravanaProductoById(@PathVariable Long id) {
        List<ProductoDTO> productoDTO = caravanaService.getCaravanaProductoById(id);
        return productoDTO;
    }

    @GetMapping("/{id}/caravanaproductos")
    public List<CaravanaProductoDTO> getCaravanaProductoDTOs(@PathVariable Long id){
        return caravanaService.getCaravanaProductoDTOs(id);
    }

    @PutMapping("/nuevojuego")
    public CaravanaDTO nuevoJuego(){
        return caravanaService.reiniciarCaravana();
    }

}
