package co.edu.javeriana.caravana_medieval.mapper;

import co.edu.javeriana.caravana_medieval.dto.ProductoDTO;
import co.edu.javeriana.caravana_medieval.model.*;
import co.edu.javeriana.caravana_medieval.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductoMapper {
    public static ProductoDTO toDTO (Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setTipo(producto.getTipo());
        productoDTO.setDescripcion(producto.getNombre());
        return productoDTO;
    }
    public static Producto toEntity (ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setId(productoDTO.getId());
        producto.setNombre(productoDTO.getNombre());
        producto.setTipo(productoDTO.getTipo());
        producto.setDescripcion(productoDTO.getDescripcion());
        return producto;
    }
}
