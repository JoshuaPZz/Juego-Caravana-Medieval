package co.edu.javeriana.caravana_medieval.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.dto.RutaCiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval.mapper.RutaMapper;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.model.Ruta;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.repository.RutaRepository;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    public List<RutaDTO> listarRutas() {
        return rutaRepository.findAll().stream()
                .map(RutaMapper::toDto).toList();
    }

    public List<RutaDTO> listarRutas(Pageable pageable) {
        return rutaRepository.findAll(pageable).stream()
                .map(RutaMapper::toDto).toList();
    }

    public Optional<RutaDTO> buscarRuta(Long id) {
        return rutaRepository.findById(id)
                .map(RutaMapper::toDto);
    }

    public Optional<RutaCiudadDTO> getRutaCiudad(Long rutaId) {
        Optional<Ruta> rutaOpt = rutaRepository.findById(rutaId);
        if (rutaOpt.isEmpty()) {
            return Optional.empty();
        }

        Ruta ruta = rutaOpt.get();
        Long origenId = (ruta.getOrigen() != null) ? ruta.getOrigen().getId() : null;
        Long destinoId = (ruta.getDestino() != null) ? ruta.getDestino().getId() : null;

        RutaCiudadDTO rutaCiudadDTO = new RutaCiudadDTO(rutaId, origenId, destinoId);
        return Optional.of(rutaCiudadDTO);
    }

    public List<RutaDTO> listaIdstoRuta(List<Long> rutasId) {
        List<RutaDTO> rutasDTO = new ArrayList<RutaDTO>();
        for (Long id : rutasId) {
            rutasDTO.add(buscarRuta(id).get());
        }
        return rutasDTO;
    }

    public RutaDTO crearRuta(RutaDTO rutaDTO) {
        rutaDTO.setId(null);
        Ruta ruta = RutaMapper.toEntity(rutaDTO);
        return RutaMapper.toDto(rutaRepository.save(ruta));
    }

    public void actualizarRutaCiudad(RutaCiudadDTO rutaCiudadDTO) {
        Ruta ruta = rutaRepository.findById(rutaCiudadDTO.getRutaId()).orElseThrow();
        Optional<Ciudad> origen = ciudadRepository.findById(rutaCiudadDTO.getCiudadOrigenId());
        Optional<Ciudad> destino = ciudadRepository.findById(rutaCiudadDTO.getCiudadDestinoId());
        ruta.setOrigen(origen.get());
        ruta.setDestino(destino.get());
        rutaRepository.save(ruta);
    }

    public void borrarRuta(Long id) {
        rutaRepository.deleteById(id);
    }

    public RutaDTO actualizarRuta(RutaDTO rutaDTO) {
        if (rutaDTO.getId() != null) {
            Ruta ruta = RutaMapper.toEntity(rutaDTO);
            return RutaMapper.toDto(rutaRepository.save(ruta));
        } else {
            return null;
        }
    }
}