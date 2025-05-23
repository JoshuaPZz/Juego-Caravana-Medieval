package co.edu.javeriana.caravana_medieval.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.javeriana.caravana_medieval.service.*;
import co.edu.javeriana.caravana_medieval.dto.*;
import co.edu.javeriana.caravana_medieval.mapper.CiudadProductoMapper;

@RestController
@RequestMapping("/productoCiudad")
public class CiudadProductoController {
    
    @Autowired
    private CiudadProductoService ciudadProductoService;
    
    @Autowired  
    private ProductoService productoService;

    @GetMapping("/list/ciudadProducto/{idCiudad}")
    public List<CiudadProductoDTO> getCiudadProducto(@PathVariable Long idCiudad) {
        return ciudadProductoService.getCiudadProducto(idCiudad).get();
    }
    @GetMapping("/list/{idCiudad}")
    public List<ProductoDTO> getProductosCiudad(@PathVariable Long idCiudad) {
        List<CiudadProductoDTO> ciudadProductosDTO = ciudadProductoService.getCiudadProducto(idCiudad).orElseThrow();
        return productoService.listaIdsToProducto(ciudadProductosDTO.stream().map(CiudadProductoDTO :: getIdProducto).toList());
    }
    @GetMapping("{idCiudad}/{idProducto}")
    public CiudadProductoDTO getProductoCiudadTupla(@PathVariable Long idCiudad, @PathVariable Long idProducto) {
        List<CiudadProductoDTO> ciudadProductosDTO = ciudadProductoService.getCiudadProducto(idCiudad).get();
        return ciudadProductoService.getCiudadProductoTupla(ciudadProductosDTO, idCiudad, idProducto);
    }
    @PostMapping
    public CiudadProductoDTO createCiudadProducto(@RequestBody CiudadProductoDTO ciudadProductoDTO) {
        return CiudadProductoMapper.toDTO(ciudadProductoService.createCiudadProducto(ciudadProductoDTO));
    }

    @PutMapping
    public CiudadProductoDTO updateCiudadProducto(@RequestBody CiudadProductoDTO ciudadProductoDTO) {
        return CiudadProductoMapper.toDTO(ciudadProductoService.updateCiudadProducto(ciudadProductoDTO));
    }
    
    @DeleteMapping("{idCiudadProducto}")
    public void deleteCiudadProducto(@PathVariable Long idCiudadProducto) {
        ciudadProductoService.deleteCiudadProducto(idCiudadProducto);
    }
}