package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import co.edu.javeriana.caravana_medieval.service.CiudadService;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import org.springframework.web.servlet.view.RedirectView;

@RestController

@RequestMapping("/ciudades")

public class CiudadController {
    private Logger log = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private CiudadService ciudadService;


    @GetMapping("/list")
    public ModelAndView getAllCiudades() {
        List<CiudadDTO> ciudades = ciudadService.getAllCiudades();
        ModelAndView modelAndView = new ModelAndView("ciudades-list");
        modelAndView.addObject("listaciudades", ciudades);
        log.info("MOSTRANDO CIUDADES: ");
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView getCiudadById(@PathVariable("id") Long id) {
        CiudadDTO ciudad = ciudadService.getCiudadById(id).orElseThrow();
        ModelAndView modelAndView = new ModelAndView("ciudad-view");
        modelAndView.addObject("ciudad", ciudad);
        log.info("MOSTRANDO CIUDAD: ");
        log.info(ciudad.getNombre());
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView formularioCrearCiudad() {
        ModelAndView modelAndView = new ModelAndView("ciudad-edit");
        modelAndView.addObject("ciudad", new CiudadDTO());
        return modelAndView;
    }

    @PostMapping("/save")
    public RedirectView guardarCiudad(@ModelAttribute CiudadDTO ciudadDTO) {
        ciudadService.guardarCiudad(ciudadDTO);
        return new RedirectView("/ciudades/list");
    }
}


    
