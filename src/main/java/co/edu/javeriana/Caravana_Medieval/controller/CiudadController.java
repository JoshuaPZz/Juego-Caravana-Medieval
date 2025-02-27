package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.PathVariable;
import co.edu.javeriana.caravana_medieval.service.CiudadService;
import co.edu.javeriana.caravana_medieval.model.Ciudad;

@Controller
@RequestMapping("/ciudades")
public class CiudadController {
    @Autowired
    private CiudadService ciudadService;


    @GetMapping("/list")
    public ModelAndView getAllCiudades() {
        List<Ciudad> ciudades = ciudadService.getAllCiudades();
        ModelAndView modelAndView = new ModelAndView("ciudades-list");
        modelAndView.addObject("listaciudades", ciudades);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
        public ModelAndView getCiudadById(@PathVariable("id") Long id) {
            Ciudad ciudad = ciudadService.getCiudadById(id);
            ModelAndView modelAndView = new ModelAndView("ciudad-view");
            modelAndView.addObject("ciudad", ciudad);
            return modelAndView;
        }
    }


    
