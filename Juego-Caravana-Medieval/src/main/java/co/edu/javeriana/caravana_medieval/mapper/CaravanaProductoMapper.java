package co.edu.javeriana.caravana_medieval.mapper;

import java.time.Duration;

import co.edu.javeriana.caravana_medieval.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval.model.CaravanaProducto;


public class CaravanaProductoMapper {
    public static CaravanaProductoDTO toDTO(CaravanaProducto caravanaProducto) {
        CaravanaProductoDTO caravanaProductoDTO = new CaravanaProductoDTO();
        caravanaProductoDTO.setCantidad(caravanaProducto.getCantidad());
        caravanaProductoDTO.setIdProducto(caravanaProducto.getProducto().getId());
        return caravanaProductoDTO;
    }
}
