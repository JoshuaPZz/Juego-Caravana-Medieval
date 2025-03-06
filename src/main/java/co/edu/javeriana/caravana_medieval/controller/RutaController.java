package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.service.CiudadService;
import co.edu.javeriana.caravana_medieval.service.RutaService;

@RestController

@RequestMapping("/rutass")

public class RutaController {
    private Logger log = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private RutaService rutaService;

    @GetMapping("/list")
    public ModelAndView getAllRutas() {
        List<RutasDTO> ciudades = rutaService.getAllCiudades();
        ModelAndView modelAndView = new ModelAndView("rutas-list");
        modelAndView.addObject("listarutas", ciudades);
        log.info("MOSTRANDO RUTAS: ");
        return modelAndView;
    }
    
}
