package co.edu.javeriana.caravana_medieval.mapper;

import co.edu.javeriana.caravana_medieval.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval.model.Ruta;

public class RutaMapper {
    public static RutaDTO toDto(Ruta ruta){
        RutaDTO rutaDTO = new RutaDTO();
        rutaDTO.setId(ruta.getId());
        rutaDTO.setDano(ruta.getDano());
        rutaDTO.setLongitud(ruta.getLongitud());

        return rutaDTO;
    }

    public static Ruta toEntity(RutaDTO rutaDTO){
        Ruta ruta = new Ruta();
        ruta.setId(rutaDTO.getId());
        ruta.setDano(rutaDTO.getDano());
        ruta.setLongitud(rutaDTO.getLongitud());
        return ruta;
    }
    
}
