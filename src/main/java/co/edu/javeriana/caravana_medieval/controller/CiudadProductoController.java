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
public class CiudadProductoController {
    private Logger log = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private CiudadService ciudadService;
    @Autowired
    private ProductoService productoService;
    @GetMapping("/producto")
    public ModelAndView getProductosCiudad(@PathVariable("id") Long id) {
        CiudadDTO ciudad = ciudadService.getCiudadById(id).get();
        List<CiudadProductoDTO> ciudadProductosDTO = ciudadService.getCiudadProducto(id).orElseThrow();
        List<ProductoDTO> productos = productoService.listaIdsToProducto(ciudadProductosDTO.stream().map(CiudadProductoDTO :: getIdProducto).toList());
        ModelAndView modelAndView = new ModelAndView("producto-ciudad-view");
        modelAndView.addObject("ciudad", ciudad);
        modelAndView.addObject("productos", productos);
        return modelAndView;
    }
    @GetMapping("producto/{idProducto}")
    public ModelAndView getProductoCiudadTupla(@PathVariable("id") Long id, @PathVariable("idProducto") Long productoId) {
        List<CiudadProductoDTO> ciudadProductosDTO = ciudadService.getCiudadProducto(id).get();
        CiudadProductoDTO ciudadProductoDTO = ciudadService.getCiudadProductoTupla(ciudadProductosDTO, id, productoId);
        ModelAndView modelAndView = new ModelAndView("producto-tupla");
        modelAndView.addObject("ciudadProducto", ciudadProductoDTO);
        return modelAndView;
    }
}
