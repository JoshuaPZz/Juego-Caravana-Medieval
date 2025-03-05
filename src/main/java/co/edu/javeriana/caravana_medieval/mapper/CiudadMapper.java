package co.edu.javeriana.caravana_medieval.mapper;

import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.model.*;

import java.util.ArrayList;
import java.util.List;

public class CiudadMapper {
    public static CiudadDTO toDTO (Ciudad ciudad) {
        CiudadDTO ciudadDTO = new CiudadDTO();
        ciudadDTO.setId(ciudad.getId());
        ciudadDTO.setNombre(ciudad.getNombre());
        ciudadDTO.setImpuesto(ciudad.getImpuesto());
        ciudadDTO.setProductoNombres(productoNombresEntidad(ciudad));
        ciudadDTO.setServicioNombres(servicioNombresEntidad(ciudad));
        ciudadDTO.setCompraServicioNombre(compraServicioEntidad(ciudad));
        ciudadDTO.setCompraCaravanaNombres(compraCaravanaEntidad(ciudad));
        ciudadDTO.setRutaOrigenNombres(rutaOrigenEntidad(ciudad));
        ciudadDTO.setRutaDestinoNombres(rutaDestinoEntidad(ciudad));
        return ciudadDTO;
    }
    public static Ciudad toEntity (CiudadDTO ciudadDTO) {
        Ciudad ciudad = new Ciudad();
        ciudad.setId(ciudadDTO.getId());
        ciudad.setNombre(ciudadDTO.getNombre());
        ciudad.setImpuesto(ciudadDTO.getImpuesto());
        return ciudad;
    }
    public static List<String> productoNombresEntidad(Ciudad ciudad){
        List<String> nombres = new ArrayList<String>();
        for(CiudadProducto producto : ciudad.getProductos()){
            nombres.add(producto.getProducto().getNombre());
        }
        return nombres;
    }
    public static List<String> servicioNombresEntidad(Ciudad ciudad){
        List<String> nombres = new ArrayList<String>();
        for(CiudadServicio servicio : ciudad.getServicios()){
            nombres.add(servicio.getServicio().getNombre());
        }
        return nombres;
    }

    public static List<String> compraServicioEntidad(Ciudad ciudad) {
        List<String> nombres = new ArrayList<String>();
        for (ServicioCompra servicioCompra : ciudad.getCompras()) {
            nombres.add(servicioCompra.getServicio().getNombre());
        }
        return nombres;
    }

    public static List<String> compraCaravanaEntidad(Ciudad ciudad) {
        List<String> nombres = new ArrayList<String>();
        for (ServicioCompra servicioCompra : ciudad.getCompras()) {
            nombres.add(servicioCompra.getCaravana().getNombre());
        }
        return nombres;
    }

    public static List<String> rutaDestinoEntidad(Ciudad ciudad){
        List<String> nombres = new ArrayList<String>();
        for (Ruta ruta : ciudad.getRutasOrigen()) {
            nombres.add(ruta.getId().toString());
        }
        return nombres;
    }
    public static List<String> rutaOrigenEntidad(Ciudad ciudad){
        List<String> nombres = new ArrayList<String>();
        for (Ruta ruta : ciudad.getRutasDestino()) {
            nombres.add(ruta.getId().toString());
        }
        return nombres;
    }
}
