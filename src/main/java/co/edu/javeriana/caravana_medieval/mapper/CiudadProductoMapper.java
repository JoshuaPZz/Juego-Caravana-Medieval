package co.edu.javeriana.caravana_medieval.mapper;
import co.edu.javeriana.caravana_medieval.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.ServicioCompraDTO;
import co.edu.javeriana.caravana_medieval.model.*;

public class CiudadProductoMapper {
    public static CiudadProductoDTO toDTO (CiudadProducto ciudadProducto) {
        CiudadProductoDTO ciudadProductoDTO = new CiudadProductoDTO();
        ciudadProductoDTO.setIdCiudad(ciudadProducto.getId());
        return ciudadProductoDTO;
    }
    public static CiudadProducto toEntity (CiudadProductoDTO ciudadProductoDTO) {
        CiudadProducto ciudadProducto = new CiudadProducto();
        ciudadProducto.setId(ciudadProductoDTO.getIdCiudad());;

        return ciudadProducto;
    }
}
