package co.edu.javeriana.caravana_medieval.mapper;

import co.edu.javeriana.caravana_medieval.dto.ServicioDTO;
import co.edu.javeriana.caravana_medieval.model.Servicio;

public class ServicioMapper {
    public static ServicioDTO toDTO (Servicio servicio) {
        ServicioDTO servicioDTO = new ServicioDTO();
        servicioDTO.setId(servicio.getId());
        servicioDTO.setNombre(servicio.getNombre());
        servicioDTO.setDescripcion(servicio.getDescripcion());
        return servicioDTO;
    }
    public static Servicio toEntity (ServicioDTO servicioDTO) {
        Servicio servicio = new Servicio();
        servicio.setId(servicioDTO.getId());
        servicio.setNombre(servicioDTO.getNombre());
        servicio.setDescripcion(servicioDTO.getDescripcion());
        return servicio;
    }

}
