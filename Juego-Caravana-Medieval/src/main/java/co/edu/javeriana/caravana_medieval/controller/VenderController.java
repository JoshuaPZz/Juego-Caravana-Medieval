package co.edu.javeriana.caravana_medieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval.service.VenderService;

@RestController
@RequestMapping("/vender")
public class VenderController {
    @Autowired
    private VenderService venderService;

    @PutMapping("/productos/{idCaravana}/{idProducto}/{cantidad}")
    public String vender(@PathVariable Long idCaravana, @PathVariable Long idProducto, @PathVariable int cantidad) {
        venderService.vender(idCaravana, idProducto, cantidad);
        return "Producto vendido exitosamente";
    }

    @PutMapping("/productos/{idCaravana}")
    public void venderProducto (@PathVariable Long idCaravana,  @RequestBody CiudadProductoDTO ciudadProductoDTO) {
        venderService.venderProducto(ciudadProductoDTO, idCaravana);
    }
    
}
