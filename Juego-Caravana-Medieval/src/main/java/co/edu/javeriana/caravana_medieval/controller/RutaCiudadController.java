package co.edu.javeriana.caravana_medieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import co.edu.javeriana.caravana_medieval.dto.RutaCiudadDTO;
import co.edu.javeriana.caravana_medieval.service.RutaService;

@RestController
@RequestMapping("/rutas/ciudades")
public class RutaCiudadController {
    @Autowired
    private RutaService rutaService;

    @GetMapping("/{rutaId}")
    public RutaCiudadDTO getRutaCiudad(@PathVariable Long rutaId) {
    return rutaService.getRutaCiudad(rutaId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ruta no encontrada"));
}       

    @PostMapping ("/save")
    public RutaCiudadDTO guardarRutaCiudades(@RequestBody RutaCiudadDTO rutaCiudadDTO) {
        rutaService.actualizarRutaCiudad(rutaCiudadDTO);
        return rutaCiudadDTO;
    }
    
}
