package co.edu.javeriana.caravana_medieval.controller;
import java.util.List;

import co.edu.javeriana.caravana_medieval.repository.ProductoRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval.service.*;
import co.edu.javeriana.caravana_medieval.dto.*;

import org.slf4j.Logger;
@Controller
@RequestMapping("/ciudades/view/{id}")
public class CiudadServicioController {
    private Logger log = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private CiudadService ciudadService;
    @Autowired
    private ServicioService servicioService;

    @GetMapping("/servicios")
    public ModelAndView getProductosCiudad(@PathVariable("id") Long id) {
        CiudadDTO ciudad = ciudadService.getCiudadById(id).get();
        CiudadServicioDTO ciudadServicioDTO = ciudadService.getCiudadService(id).orElseThrow();
        List<ServicioDTO> servicios = servicioService.listaIdsToProducto(ciudadServicioDTO.getIdServicios());
        ModelAndView modelAndView = new ModelAndView("servicio-ciudad-view");
        modelAndView.addObject("ciudad", ciudad);
        modelAndView.addObject("servicios", servicios);
        return modelAndView;
    }
}
