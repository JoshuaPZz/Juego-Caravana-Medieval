package co.edu.javeriana.caravana_medieval.service;

import java.util.List;

import co.edu.javeriana.caravana_medieval.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadProductoDTO;

public class CaravanaProductoService {
    public CaravanaProductoDTO getCaravaanProductoTupla(List<CaravanaProductoDTO> ciudadProductosDTO, Long idCaravana, Long idProducto) {
        return ciudadProductosDTO.stream()
        .filter(x -> x.getIdProducto() == idProducto).
                findFirst()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
