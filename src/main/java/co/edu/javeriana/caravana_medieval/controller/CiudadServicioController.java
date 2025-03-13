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
import org.springframework.web.servlet.view.RedirectView;

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
        List<CiudadServicioDTO> ciudadServiciosDTO = ciudadService.getCiudadService(id).orElseThrow();
        List<ServicioDTO> servicios = servicioService.listaIdsToProducto(ciudadServiciosDTO.stream().map(CiudadServicioDTO::getIdServicio).toList());
        ModelAndView modelAndView = new ModelAndView("servicio-ciudad-view");
        modelAndView.addObject("ciudad", ciudad);
        modelAndView.addObject("servicios", servicios);
        return modelAndView;
    }

    @GetMapping("servicios/{idServicio}")
    public ModelAndView getServicioCiudadTupla(@PathVariable("id") Long id, @PathVariable("idServicio") Long idServicio) {
        List<CiudadServicioDTO> ciudadServiciosDTO = ciudadService.getCiudadService(id).get();
        CiudadServicioDTO ciudadServicioDTO = ciudadService.getCiudadServicioTupla(ciudadServiciosDTO, id, idServicio);
        ModelAndView modelAndView = new ModelAndView("servicio-tupla");
        modelAndView.addObject("ciudadServicio", ciudadServicioDTO);
        return modelAndView;
    }

    @PostMapping("servicio/{idServicio}/save")
    public RedirectView saveEditServicioCiudad(@ModelAttribute CiudadServicioDTO ciudadServicioDTO, @PathVariable("id") Long id, @PathVariable Long idServicio) {
        ciudadServicioDTO.setIdCiudad(id);
        ciudadServicioDTO.setIdServicio(idServicio);
        ciudadService.saveEditServicioCiudad(ciudadServicioDTO);
        return new RedirectView("/ciudades/view/" + id + "/servicios");
    }

    @GetMapping("servicio/{idServicio}/delete")
    public RedirectView deleteServicioCiudad(@PathVariable("id") Long idCiudad, @PathVariable Long idServicio) {
        log.error("id de ciudad: "+ idCiudad.toString());
        log.error("id de servocio: "+ idServicio.toString());
        ciudadService.borrarCiudadServicio(idCiudad, idServicio);
        return new RedirectView("/ciudades/view/" + idCiudad + "/servicios");
    }


}
