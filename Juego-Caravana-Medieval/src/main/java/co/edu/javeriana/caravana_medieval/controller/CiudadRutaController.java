package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadRutasDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval.service.CiudadService;
import co.edu.javeriana.caravana_medieval.service.RutaService;

@Controller
@RequestMapping("/ciudades/view/{id}")
public class CiudadRutaController {
    @Autowired
    private CiudadService ciudadService;
    @Autowired
    private RutaService rutaService;

    @GetMapping("/rutaorigen")
    public ModelAndView getRutasOrigen(@PathVariable("id") Long id) {
        CiudadDTO ciudad = ciudadService.getCiudadById(id).get();
        CiudadRutasDTO ciudadRutasDTO = ciudadService.getCiudadRutas(id).orElseThrow();
        List<RutaDTO> rutasOrigen = rutaService.listaIdstoRuta(ciudadRutasDTO.getIdRutasOrigen());
        ModelAndView modelAndView = new ModelAndView("rutaorigen-ciudad-view");
        modelAndView.addObject("ciudad", ciudad);
        modelAndView.addObject("rutasOrigen", rutasOrigen);
        return modelAndView;        
    } 
    
    @GetMapping("/rutadestino")
    public ModelAndView getRutasDestino(@PathVariable("id") Long id) {
        CiudadDTO ciudad = ciudadService.getCiudadById(id).get();
        CiudadRutasDTO ciudadRutasDTO = ciudadService.getCiudadRutas(id).orElseThrow();
        List<RutaDTO> rutasDestino = rutaService.listaIdstoRuta(ciudadRutasDTO.getIdRutasDestino());
        ModelAndView modelAndView = new ModelAndView("rutadestino-ciudad-view");
        modelAndView.addObject("ciudad", ciudad);
        modelAndView.addObject("rutasDestino", rutasDestino);
        return modelAndView;        
    }
    
    

    
    
}
