package co.edu.javeriana.caravana_medieval.mapper;
import co.edu.javeriana.caravana_medieval.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.ServicioCompraDTO;
import co.edu.javeriana.caravana_medieval.model.*;

public class CiudadProductoMapper {
    public static CiudadProductoDTO toDTO (CiudadProducto ciudadProducto) {
        CiudadProductoDTO ciudadProductoDTO = new CiudadProductoDTO();
        ciudadProductoDTO.setId(ciudadProducto.getId());
        ciudadProductoDTO.setIdCiudad(ciudadProducto.getCiudad().getId());
        ciudadProductoDTO.setIdProducto(ciudadProducto.getProducto().getId());
        ciudadProductoDTO.setNombreProducto(ciudadProducto.getProducto().getNombre());
        ciudadProductoDTO.setFactorDemanda(ciudadProducto.getFactorDemanda());
        ciudadProductoDTO.setFactorOferta(ciudadProducto.getFactorOferta());
        ciudadProductoDTO.setPrecioCompra(ciudadProducto.getPrecioCompra());
        ciudadProductoDTO.setPrecioVenta(ciudadProducto.getPrecioVenta());
        ciudadProductoDTO.setStock(ciudadProducto.getStock());        
        return ciudadProductoDTO;
    }
    public static CiudadProducto toEntity (CiudadProductoDTO ciudadProductoDTO) {
        CiudadProducto ciudadProducto = new CiudadProducto();
        ciudadProducto.setId(ciudadProductoDTO.getId());

        return ciudadProducto;
    }
}
