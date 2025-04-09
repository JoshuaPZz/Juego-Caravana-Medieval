package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import co.edu.javeriana.caravana_medieval.service.*;
import co.edu.javeriana.caravana_medieval.dto.*;
import co.edu.javeriana.caravana_medieval.mapper.CiudadServicioMapper;

@RestController
@RequestMapping("/ciudad")
public class CiudadServicioController {
    @Autowired
    private ServicioService servicioService;
    @Autowired
    private CiudadServicioService ciudadServicioService;

    @GetMapping("/{id}/servicios/list")
    public List<ServicioDTO> getServiciosCiudad(@PathVariable("id") Long id) {
        List<CiudadServicioDTO> ciudadServiciosDTO = ciudadServicioService.getCiudadService(id).orElseThrow();
        return servicioService.listaIdsToServicio(ciudadServiciosDTO.stream().map(CiudadServicioDTO::getIdServicio).toList());
    }

    @GetMapping("/{id}/ciudadservicios")
    public List<CiudadServicioDTO> getCiudadServicios(@PathVariable("id") Long id) {
        return ciudadServicioService.getCiudadService(id).orElseThrow();
    }

    @GetMapping("/{id}/serviciosview/{idCiudadServicio}")
    public CiudadServicioDTO getServicioCiudadTupla(@PathVariable("id") Long id, @PathVariable("idCiudadServicio") Long idCiudadServicio) {
        List<CiudadServicioDTO> ciudadServiciosDTO = ciudadServicioService.getCiudadService(id).get();
        return ciudadServicioService.getCiudadServicioTupla(ciudadServiciosDTO, idCiudadServicio);
    }

    @PostMapping
    public CiudadServicioDTO createServicioCiudad(@RequestBody CiudadServicioDTO ciudadServicioDTO) {
        return CiudadServicioMapper.toDTO(ciudadServicioService.createCiudadServicio(ciudadServicioDTO));
    }    

    @PutMapping
    public CiudadServicioDTO updateServicioCiudad(@RequestBody CiudadServicioDTO ciudadServicioDTO) {
        return CiudadServicioMapper.toDTO(ciudadServicioService.updateCiudadServicio(ciudadServicioDTO));
    }

    @DeleteMapping("{idCiudadServicio}")
    public void borrarCiudadServicio(@PathVariable("idCiudadServicio") Long idCiudadServicio){
        ciudadServicioService.deleteCiudadServicio(idCiudadServicio);
    }

}
