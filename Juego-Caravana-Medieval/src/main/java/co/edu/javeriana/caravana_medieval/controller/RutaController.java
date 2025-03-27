package co.edu.javeriana.caravana_medieval.controller;

import java.lang.module.ResolutionException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import co.edu.javeriana.caravana_medieval.dto.ErrorDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaCiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval.mapper.RutaMapper;
import co.edu.javeriana.caravana_medieval.model.Ruta;
import co.edu.javeriana.caravana_medieval.service.RutaService;

@RestController
@RequestMapping("/rutas")
public class RutaController {

    private Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private RutaService rutaService;

    @GetMapping("/list")
    public List<RutaDTO> listarRutas() {
        return rutaService.listarRutas();
    }

    @GetMapping("/list/{page}")
    public ResponseEntity<?> listarRutas(@PathVariable Integer page) {
        if(page > 0){
            return ResponseEntity.ok(rutaService.listarRutas(PageRequest.of(page, 10)));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("El indice de la página no puede ser negativo"));
        }
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> obtenerRutaPorId(@PathVariable("id") Long id) {
        if (id > 0){
            return ResponseEntity.ok(rutaService.buscarRuta(id).orElseThrow());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("El id de la ruta no puede ser negativo"));
        }
    }

    @PostMapping
    public RutaDTO crearRuta(@RequestBody RutaDTO rutaDTO) {
        return rutaService.crearRuta(rutaDTO);
    }

    @PutMapping
    public RutaDTO actualizarRuta(@RequestBody RutaDTO rutaDTO) {
        return rutaService.actualizarRuta(rutaDTO);
    }

    @DeleteMapping("{id}")
    public void borrarRuta(@PathVariable Long id) {
        rutaService.borrarRuta(id);
    }

}