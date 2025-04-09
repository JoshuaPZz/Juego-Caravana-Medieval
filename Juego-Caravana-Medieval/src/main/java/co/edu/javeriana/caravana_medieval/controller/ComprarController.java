package co.edu.javeriana.caravana_medieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.javeriana.caravana_medieval.service.ComprarService;

@RestController
@RequestMapping("/comprar")
public class ComprarController {
    @Autowired
    private ComprarService comprarService;

    @PutMapping("/servicios/{idServicio}/{idCaravana}")
    public void  comprarServicio(@PathVariable Long idServicio, @PathVariable Long idCaravana) {
        comprarService.comprarServicio(idCaravana, idServicio);
        // return "Servicio comprado exitosamente";
    }
}
