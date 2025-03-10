package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import co.edu.javeriana.caravana_medieval.dto.RutaCiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval.service.RutaService;

@RestController

@RequestMapping("/rutas")

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

    @GetMapping("/view/{id}")
    public ModelAndView obtenerRutaPorId(@PathVariable("id") Long id){
        RutaDTO ruta = rutaService.buscarRuta(id).orElseThrow();
        RutaCiudadDTO rutaCiudad = rutaService.getRutaCiudad(id).orElseThrow();
        ModelAndView modelAndView = new ModelAndView("ruta-view");
        modelAndView.addObject("ruta", ruta);
        modelAndView.addObject("rutaCiudad", rutaCiudad);
        log.info("MOSTRANDO RUTA: ");
        log.info(ruta.getId().toString());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView formularioCrearRuta(){
        ModelAndView modelAndView = new ModelAndView("ruta-edit");
        modelAndView.addObject("ruta", new RutaDTO());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView formularioEditarRuta(@PathVariable Long id){
        RutaDTO rutaDTO = rutaService.buscarRuta(id).orElseThrow();
        ModelAndView modelAndView = new ModelAndView("ruta-edit");
        modelAndView.addObject("ruta", new RutaDTO());
        modelAndView.addObject("ruta", rutaDTO);
        return modelAndView;
    }

    @PostMapping("/save")
    public RedirectView guardarRuta (@ModelAttribute RutaDTO rutaDTO){
        rutaService.guardarRuta(rutaDTO);
        return new RedirectView("/rutas/list");
    }

    @GetMapping("delete/{id}")
    public RedirectView borrarRuta (@PathVariable Long id){
        rutaService.borrarRuta(id);
        return new RedirectView("/rutas/list");
    }
    
}
