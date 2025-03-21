package co.edu.javeriana.caravana_medieval.controller;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import co.edu.javeriana.caravana_medieval.service.*;
import co.edu.javeriana.caravana_medieval.dto.*;
import co.edu.javeriana.caravana_medieval.mapper.CiudadProductoMapper;

import org.slf4j.Logger;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping("/productoCiudad")
public class CiudadProductoController {
    private Logger log = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private CiudadService ciudadService;
    @Autowired  
    private ProductoService productoService;
    @GetMapping("/list/{idCiudad}")
    public List<ProductoDTO> getProductosCiudad(@PathVariable Long idCiudad) {
        List<CiudadProductoDTO> ciudadProductosDTO = ciudadService.getCiudadProducto(idCiudad).orElseThrow();
        return productoService.listaIdsToProducto(ciudadProductosDTO.stream().map(CiudadProductoDTO :: getIdProducto).toList());
    }
    @GetMapping("{idCiudad}/{idProducto}")
    public CiudadProductoDTO getProductoCiudadTupla(@PathVariable Long idCiudad, @PathVariable Long idProducto) {
        List<CiudadProductoDTO> ciudadProductosDTO = ciudadService.getCiudadProducto(idCiudad).get();
        return ciudadService.getCiudadProductoTupla(ciudadProductosDTO, idCiudad, idProducto);
    }
    @PostMapping
    public CiudadProductoDTO crearCiudadProducto(@RequestBody CiudadProductoDTO ciudadProductoDTO) {
        return CiudadProductoMapper.toDTO(ciudadService.crearCiudadProducto(ciudadProductoDTO));
    }

    @PutMapping
    public void actualizarCiudadProducto(@RequestBody CiudadProductoDTO ciudadProductoDTO) {
        ciudadService.actualizarCiudadProducto(ciudadProductoDTO);
    }
}
