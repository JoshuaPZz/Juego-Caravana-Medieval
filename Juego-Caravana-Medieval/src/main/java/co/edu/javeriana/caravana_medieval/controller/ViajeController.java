package co.edu.javeriana.caravana_medieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.service.ViajeService;

@RestController

@RequestMapping("/viaje")

public class ViajeController {
    @Autowired
    private ViajeService viajeService;

    @GetMapping("/ciudadActual/{id}")
    public CiudadDTO getCiudadActual(@PathVariable Long id) {
        return viajeService.getCiudadActual(id);
    }
    
}
