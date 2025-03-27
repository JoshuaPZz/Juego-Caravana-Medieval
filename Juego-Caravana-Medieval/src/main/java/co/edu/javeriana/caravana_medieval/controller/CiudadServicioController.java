package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import co.edu.javeriana.caravana_medieval.repository.ProductoRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import co.edu.javeriana.caravana_medieval.service.*;
import co.edu.javeriana.caravana_medieval.dto.*;
import co.edu.javeriana.caravana_medieval.mapper.CiudadServicioMapper;
import co.edu.javeriana.caravana_medieval.model.Ciudad;

import org.slf4j.Logger;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/ciudad/{id}/servicios")
public class CiudadServicioController {
    private Logger log = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private CiudadService ciudadService;
    @Autowired
    private ServicioService servicioService;

    @GetMapping("/list")
    public List<ServicioDTO> getServiciosCiudad(@PathVariable("id") Long id) {
        List<CiudadServicioDTO> ciudadServiciosDTO = ciudadService.getCiudadService(id).orElseThrow();
        return servicioService.listaIdsToServicio(ciudadServiciosDTO.stream().map(CiudadServicioDTO::getIdServicio).toList());
    }

    @GetMapping("view/{idCiudadServicio}")
    public CiudadServicioDTO getServicioCiudadTupla(@PathVariable("id") Long id, @PathVariable("idCiudadServicio") Long idCiudadServicio) {
        List<CiudadServicioDTO> ciudadServiciosDTO = ciudadService.getCiudadService(id).get();
        return ciudadService.getCiudadServicioTupla(ciudadServiciosDTO, idCiudadServicio);
    }

    @PostMapping
    public CiudadServicioDTO createServicioCiudad(@RequestBody CiudadServicioDTO ciudadServicioDTO) {
        return CiudadServicioMapper.toDTO(ciudadService.createCiudadServicio(ciudadServicioDTO));
    }    

    @PutMapping
    public CiudadServicioDTO updateServicioCiudad(@RequestBody CiudadServicioDTO ciudadServicioDTO) {
        return CiudadServicioMapper.toDTO(ciudadService.updateCiudadServicio(ciudadServicioDTO));
    }

    @DeleteMapping("{idCiudadServicio}")
    public void borrarCiudadServicio(@PathVariable("idCiudadServicio") Long idCiudadServicio){
        ciudadService.deleteCiudadServicio(idCiudadServicio);
    }

}
