package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaCiudadDTO;
import co.edu.javeriana.caravana_medieval.service.CiudadService;
import co.edu.javeriana.caravana_medieval.service.RutaService;

@Controller
@RequestMapping("/rutas/ciudades")
public class RutaCiudadController {
    @Autowired
    private RutaService rutaService;
    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/edit/{rutaId}")
    public ModelAndView editRutaCiudadesForm(@PathVariable Long rutaId){
        RutaCiudadDTO rutaCiudadDTO = rutaService.getRutaCiudad(rutaId).orElseThrow();
        List<CiudadDTO> allCiudades = ciudadService.getAllCiudades();
        ModelAndView modelAndView = new ModelAndView("rutas-ciudades-edit");
        modelAndView.addObject("rutaCiudad", rutaCiudadDTO);
        modelAndView.addObject("allCiudades", allCiudades);
        return modelAndView;
    }

    @PostMapping("/save")
    public RedirectView guardarRutaCiudades(@ModelAttribute RutaCiudadDTO rutaCiudadDTO){
        rutaService.actualizarRutaCiudad(rutaCiudadDTO);
        return new RedirectView("/rutas/list");
    }
    
}
