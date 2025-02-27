package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import co.edu.javeriana.caravana_medieval.service.CiudadService;
import co.edu.javeriana.caravana_medieval.model.Ciudad;

@RestController

@RequestMapping("/ciudades")

public class CiudadController {
    private Logger log = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private CiudadService ciudadService;


    @GetMapping("/list")
    public ModelAndView getAllCiudades() {
        List<Ciudad> ciudades = ciudadService.getAllCiudades();
        ModelAndView modelAndView = new ModelAndView("ciudades-list");
        modelAndView.addObject("listaciudades", ciudades);
        log.info("MOSTRANDO CIUDADES: ");
        return modelAndView;
    }

    @GetMapping("/view/{id}")
        public ModelAndView getCiudadById(@PathVariable("id") Long id) {
            Ciudad ciudad = ciudadService.getCiudadById(id);
            ModelAndView modelAndView = new ModelAndView("ciudad-view");
            modelAndView.addObject("ciudad", ciudad);
            log.info("MOSTRANDO CIUDAD: ");
            log.info(ciudad.getNombre());
            return modelAndView;
        }

    }


    
