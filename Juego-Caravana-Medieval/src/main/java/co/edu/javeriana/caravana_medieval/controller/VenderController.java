package co.edu.javeriana.caravana_medieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.service.VenderService;

@RestController
@RequestMapping("/vender")
public class VenderController {
    @Autowired
    private VenderService venderService;

    @PutMapping("/productos/{idCaravana}/{idProducto}/{cantidad}")
    public String venderProducto(@PathVariable Long idCaravana, @PathVariable Long idProducto, @PathVariable int cantidad) {
        venderService.vender(idCaravana, idProducto, cantidad);
        return "Producto vendido exitosamente";
    }


    
}
