package co.edu.javeriana.caravana_medieval.mapper;

import co.edu.javeriana.caravana_medieval.dto.CiudadServicioDTO;
import co.edu.javeriana.caravana_medieval.model.CiudadServicio;

public class CiudadServicioMapper {
    public static CiudadServicioDTO toDTO(CiudadServicio ciudadServicio){
        CiudadServicioDTO ciudadServicioDTO = new CiudadServicioDTO();
        ciudadServicioDTO.setId(ciudadServicio.getId());
        ciudadServicioDTO.setIdCiudad(ciudadServicio.getCiudad().getId());
        ciudadServicioDTO.setIdServicio(ciudadServicio.getServicio().getId());
        ciudadServicioDTO.setPrecio(ciudadServicio.getPrecio());
        ciudadServicioDTO.setNombreServicio(ciudadServicio.getServicio().getNombre());
        return ciudadServicioDTO;
    }

    public static CiudadServicio toEntity (CiudadServicioDTO ciudadServicioDTO){
        CiudadServicio ciudadServicio = new CiudadServicio();
        ciudadServicio.getServicio().setNombre(ciudadServicioDTO.getNombreServicio());;
        ciudadServicio.setId(ciudadServicioDTO.getId());
        ciudadServicio.getCiudad().setId(ciudadServicioDTO.getIdCiudad());
        ciudadServicio.getServicio().setId(ciudadServicioDTO.getIdServicio());
        ciudadServicio.setPrecio(ciudadServicioDTO.getPrecio());
        return ciudadServicio;
    }
}
