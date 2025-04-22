package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadRutasDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval.service.CiudadRutaService;

import co.edu.javeriana.caravana_medieval.service.RutaService;

@RestController
@RequestMapping("/ciudad")
public class CiudadRutaController {
    
    @Autowired
    private RutaService rutaService;
    @Autowired
    private CiudadRutaService ciudadRutaService;

    @GetMapping("/{id}/ciudadesorigen")
    public List<CiudadDTO> getCiudadesRutaOrigen(@PathVariable("id") Long id) {
        CiudadRutasDTO ciudadRutasDTO = ciudadRutaService.getCiudadRutas(id).orElseThrow();
        return ciudadRutaService.getDestinosCiudad(ciudadRutasDTO).get();
    }

    @GetMapping("/{id}/rutasorigen")
    public List<RutaDTO> getRutasOrigen(@PathVariable("id") Long id) {
        CiudadRutasDTO ciudadRutasDTO = ciudadRutaService.getCiudadRutas(id).orElseThrow();
        return ciudadRutaService.getRutasOrigenCiudad(ciudadRutasDTO).get();
    }
    
    @GetMapping("/{id}/rutadestino")
    public List<RutaDTO> getRutasDestino(@PathVariable("id") Long id) {
        CiudadRutasDTO ciudadRutasDTO = ciudadRutaService.getCiudadRutas(id).orElseThrow();
        return rutaService.listaIdstoRuta(ciudadRutasDTO.getIdRutasDestino());
    }

    @GetMapping("/{id}/rutashacia/{idDestino}")
    public List<RutaDTO> getRutasCiudadAOtra(@PathVariable("id") Long id, @PathVariable("idDestino") Long idDestino) {
        return ciudadRutaService.getRutasCiudadAOtra(id, idDestino).get();
    }
    
}
