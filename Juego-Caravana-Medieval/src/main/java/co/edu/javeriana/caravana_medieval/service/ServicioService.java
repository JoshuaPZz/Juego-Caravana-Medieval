package co.edu.javeriana.caravana_medieval.service;

import co.edu.javeriana.caravana_medieval.dto.ServicioDTO;
import co.edu.javeriana.caravana_medieval.mapper.ServicioMapper;
import co.edu.javeriana.caravana_medieval.model.Producto;
import co.edu.javeriana.caravana_medieval.model.Servicio;
import co.edu.javeriana.caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {
    @Autowired
    private ServicioRepository servicioRepository;

    public List<ServicioDTO> getAllServicios() {
        return servicioRepository.findAll().stream()
                .map(ServicioMapper::toDTO).toList();
    }

    public Optional<ServicioDTO> getProductoById(Long id) {
        return servicioRepository.findById(id)
                .map(ServicioMapper::toDTO);
    }

    public void guardarProducto(ServicioDTO servicioDTO) {
        Servicio servicio = ServicioMapper.toEntity(servicioDTO);
        servicioRepository.save(servicio);

    }
    public List<ServicioDTO> listaIdsToProducto (List<Long> serviciosId) {
        List<ServicioDTO> serviciosDTO = new ArrayList<ServicioDTO>();
        for(Long id : serviciosId) {
            serviciosDTO.add(getProductoById(id).get());
        }
        return serviciosDTO;
    }
}
