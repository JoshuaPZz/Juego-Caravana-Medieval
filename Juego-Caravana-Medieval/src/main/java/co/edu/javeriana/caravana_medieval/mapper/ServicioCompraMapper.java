package co.edu.javeriana.caravana_medieval.mapper;
import co.edu.javeriana.caravana_medieval.dto.ServicioCompraDTO;
import co.edu.javeriana.caravana_medieval.model.*;

public class ServicioCompraMapper {
    public static ServicioCompraDTO toDTO (ServicioCompra servicioCompra) {
        ServicioCompraDTO servicioCompraDTO = new ServicioCompraDTO();
        servicioCompraDTO.setId(servicioCompra.getId());
        servicioCompraDTO.setIdCiudad(servicioCompra.getCiudad().getId());
        servicioCompraDTO.setIdServicio(servicioCompra.getServicio().getId());
        servicioCompraDTO.setIdCaravana(servicioCompra.getCaravana().getId());
        servicioCompraDTO.setNombreServicio(servicioCompra.getServicio().getNombre());
        servicioCompraDTO.setNombreCaravana(servicioCompra.getCaravana().getNombre());
        return servicioCompraDTO;
    }
    public static ServicioCompra toEntity (ServicioCompraDTO servicioCompraDTO) {
        ServicioCompra servicioCompra = new ServicioCompra();
        servicioCompra.setId(servicioCompra.getId());
        servicioCompra.getCiudad().setId(servicioCompraDTO.getIdCiudad());
        servicioCompra.getServicio().setId(servicioCompraDTO.getIdServicio());
        servicioCompra.getCaravana().setId(servicioCompraDTO.getIdCaravana());
        return servicioCompra;
    }
}
