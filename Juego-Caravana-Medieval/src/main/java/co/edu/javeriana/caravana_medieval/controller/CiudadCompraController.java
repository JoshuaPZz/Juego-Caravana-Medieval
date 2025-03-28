package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval.service.*;
import co.edu.javeriana.caravana_medieval.dto.*;

@Controller
@RequestMapping("/ciudades/view/{id}")
public class CiudadCompraController {
    
    @Autowired
    private CiudadCompraService ciudadCompraService;

    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/compras")
    public ModelAndView getProductosCiudad(@PathVariable("id") Long id) {
        CiudadDTO ciudad = ciudadService.getCiudadById(id).get();
        List<ServicioCompraDTO> compras = ciudadCompraService.getCiudadCompras(id).get();
        ModelAndView modelAndView = new ModelAndView("compras-ciudad-view");
        modelAndView.addObject("ciudad", ciudad);
        modelAndView.addObject("compras", compras);
        return modelAndView;
    }
}
