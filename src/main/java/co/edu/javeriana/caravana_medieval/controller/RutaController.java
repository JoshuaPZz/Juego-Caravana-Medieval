package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval.service.RutaService;

@RestController

@RequestMapping("/rutass")

public class RutaController {
    private Logger log = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private RutaService rutaService;

    @GetMapping("/list")
    public ModelAndView listarRutas() {
        List<RutaDTO> rutas = rutaService.listarRutas();
        ModelAndView modelAndView = new ModelAndView("rutas-list");
        modelAndView.addObject("listarutas", rutas);
        log.info("MOSTRANDO RUTAS: ");
        return modelAndView;
    }
    
    /*@GetMapping("/view/{idRuta}")
    public ModelAndView buscarRuta(@PathVariable("idRuta") Long id){
        RutaDTO ruta = rutaService.buscarRuta(id).orElseThrow();
        ModelAndView modelAndView = new ModelAndView("rutas-view");
        modelAndView.addObject("ruta", ruta);
        return modelAndView;
    }*/
}
