package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.ProductoDTO;
import co.edu.javeriana.caravana_medieval.model.Producto;
import co.edu.javeriana.caravana_medieval.service.ProductoService;

@RestController

@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/list")
    public List<ProductoDTO>  getAllProductos() {
        List<ProductoDTO> productos = productoService.getAllProductos();
        return productos;
    }

    @GetMapping("/{id}")
    public ProductoDTO getProductoById(@PathVariable("id") Long id) {
        ProductoDTO producto = productoService.getProductoById(id).orElseThrow();
        return producto;
    }

    @PostMapping
    public Producto createProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.createProducto(productoDTO);
    }

    @PutMapping
    public Producto editProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.updateProducto(productoDTO);
    }

    @DeleteMapping("/{id}")
    public String  deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return "Borrado";
    }    
}
