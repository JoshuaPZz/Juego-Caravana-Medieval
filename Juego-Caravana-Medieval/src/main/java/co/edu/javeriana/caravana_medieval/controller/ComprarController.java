package co.edu.javeriana.caravana_medieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval.service.ComprarService;

@RestController
@RequestMapping("/comprar")
public class ComprarController {
    @Autowired
    private ComprarService comprarService;

    @PutMapping("/servicios/{idServicio}/{idCaravana}")
    public void  comprarServicio(@PathVariable Long idServicio, @PathVariable Long idCaravana) {
        comprarService.comprarServicio(idServicio, idCaravana);
        // return "Servicio comprado exitosamente";
    }

    @PutMapping("productos/{idProducto}/{idCaravana}")
    public void comprarProducto(@PathVariable Long idProducto, @PathVariable Long idCaravana) {
        comprarService.comprarProducto(idProducto, idCaravana); 
    }
 }
