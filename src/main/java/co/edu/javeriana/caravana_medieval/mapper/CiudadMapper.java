package co.edu.javeriana.caravana_medieval.mapper;

import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.model.Ciudad;

public class CiudadMapper {
    public static CiudadDTO toDTO (Ciudad ciudad) {
        CiudadDTO ciudadDTO = new CiudadDTO();
        ciudadDTO.setNombre(ciudad.getNombre());
        ciudadDTO.setImpuesto(ciudad.getImpuesto());
        return ciudadDTO;
    }
    public static Ciudad toEntity (CiudadDTO ciudadDTO) {
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(ciudadDTO.getNombre());
        ciudad.setImpuesto(ciudadDTO.getImpuesto());
        return ciudad;
    }
}
