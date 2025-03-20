package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import co.edu.javeriana.caravana_medieval.service.CiudadService;
import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import org.springframework.web.servlet.view.RedirectView;

@RestController

@RequestMapping("/ciudades")

public class CiudadController {
    private Logger log = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private CiudadService ciudadService;


    @GetMapping("/list")
    public List<CiudadDTO>  getAllCiudades() {
        List<CiudadDTO> ciudades = ciudadService.getAllCiudades();
        return ciudades;
    }

    @GetMapping("/{id}")
    public CiudadDTO getCiudadById(@PathVariable("id") Long id) {
        CiudadDTO ciudad = ciudadService.getCiudadById(id).orElseThrow();
        return ciudad;
    }

    @PostMapping
    public CiudadDTO createCiudad(@RequestBody CiudadDTO ciudadDTO) {
        ciudadService.createCiudad(ciudadDTO);
        return ciudadDTO;
    }

    @PutMapping
    public CiudadDTO editCiudad(@RequestBody CiudadDTO ciudadDTO) {
        if(ciudadDTO!=null){
        ciudadService.editCiudad(ciudadDTO);
        return ciudadDTO;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String  borrarCiudad(@PathVariable Long id) {
        ciudadService.borrarAllCiudadServicio(id);
        return "Borrado";
    }
}


    
