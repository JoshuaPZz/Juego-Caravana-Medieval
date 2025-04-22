package co.edu.javeriana.caravana_medieval.mapper;

import java.time.Duration;

import co.edu.javeriana.caravana_medieval.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval.model.Caravana;

public class CaravanaMapper {
    public static CaravanaDTO toDTO(Caravana caravana) {
        CaravanaDTO caravanaDTO = new CaravanaDTO();
        caravanaDTO.setId(caravana.getId());
        caravanaDTO.setNombre(caravana.getNombre());
        caravanaDTO.setVelocidad(caravana.getVelocidad());
        caravanaDTO.setCapacidadMax(caravana.getCapacidadMax());
        caravanaDTO.setDineroDisponible(caravana.getDineroDisponible());
        caravanaDTO.setPuntosVida(caravana.getPuntosVida());
        caravanaDTO.setHoraViaje(caravana.getHoraViaje());
        caravanaDTO.setTieneGuardias(caravana.isTieneGuardias());
        caravanaDTO.setTiempoTranscurrido(caravana.getTiempoTranscurrido().toSeconds());
        return caravanaDTO;
    }
    public static Caravana toEntity(CaravanaDTO caravanaDTO) {
        Caravana caravana = new Caravana();
        caravana.setId(caravanaDTO.getId());
        caravana.setNombre(caravanaDTO.getNombre());
        caravana.setVelocidad(caravanaDTO.getVelocidad());
        caravana.setCapacidadMax(caravanaDTO.getCapacidadMax());
        caravana.setDineroDisponible(caravanaDTO.getDineroDisponible());
        caravana.setPuntosVida(caravanaDTO.getPuntosVida());
        caravana.setHoraViaje(caravanaDTO.getHoraViaje());
        caravana.setTieneGuardias(caravanaDTO.isTieneGuardias());
        caravana.setTiempoTranscurrido(Duration.ofSeconds(caravana.getTiempoTranscurrido().toSeconds()));
        return caravana;
    }
}
