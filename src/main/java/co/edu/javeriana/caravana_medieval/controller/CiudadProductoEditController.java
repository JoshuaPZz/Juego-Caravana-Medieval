package co.edu.javeriana.caravana_medieval.controller;
import java.util.ArrayList;
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
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/ciudades/edit/{id}")
public class CiudadProductoEditController {
    private Logger log = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private CiudadService ciudadService;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/productos")
    public ModelAndView getProductosCiudad(@PathVariable("id") Long id) {
        CiudadDTO ciudad = ciudadService.getCiudadById(id).get();
        log.info("Ciudad con id " + ciudad.getId() + " " + ciudad.getNombre() );
        CiudadProductoDTO  productosCiudad =ciudadService.getCiudadProducto(id).get();
        List<ProductoDTO> productos = productoService.getAllProductos();
        ModelAndView modelAndView = new ModelAndView("ciudad-productos-edit");
        modelAndView.addObject("ciudad", ciudad);
        modelAndView.addObject("productos", productos);
        modelAndView.addObject("productosCiudad", productosCiudad);
        return modelAndView;
    }
    @PostMapping("/save")
    public RedirectView saveProductosCiudad(@ModelAttribute CiudadProductoDTO ciudadProductoDTO) {
        log.info("Ahora recibidos con " + ciudadProductoDTO.getIdCiudad());
        ciudadService.updateCiudadProducto(ciudadProductoDTO);
        return new RedirectView("/ciudades/list");
    }
}
