package co.edu.javeriana.caravana_medieval.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import co.edu.javeriana.caravana_medieval.dto.*;
import co.edu.javeriana.caravana_medieval.model.*;
import co.edu.javeriana.caravana_medieval.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.javeriana.caravana_medieval.mapper.CiudadMapper;

@Service
public class CiudadRutaService {
    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private RutaService rutaService;

    @Autowired
    private CiudadService ciudadService;

    public Optional<CiudadRutasDTO> getCiudadRutas(Long ciudadId) {
        Optional<Ciudad> ciudadOpt = ciudadRepository.findById(ciudadId);
        if (ciudadOpt.isEmpty()) {
            return Optional.empty();
        }
        Ciudad ciudad = ciudadOpt.get();
        List<Ruta> relacionRutaOrigen = ciudad.getRutasOrigen();
        List<Long> idRutasOrigen = relacionRutaOrigen.stream()
                .map(Ruta::getId)
                .toList();
        List<Ruta> relacionRutaDestino = ciudad.getRutasDestino();
        List<Long> idRutasDestino = relacionRutaDestino.stream()
                .map(Ruta::getId)
                .toList();
        CiudadRutasDTO ciudadRutasDTO = new CiudadRutasDTO(ciudadId, idRutasOrigen, idRutasDestino);
        return Optional.of(ciudadRutasDTO);
    }

    public Optional<List<RutaDTO>> getRutasOrigenCiudad(CiudadRutasDTO ciudadRutasDTO) {
        return Optional.of(rutaService.listaIdstoRuta(ciudadRutasDTO.getIdRutasOrigen())
                .stream()
                .toList());

    }

    public Optional<List<CiudadDTO>> getDestinosCiudad (CiudadRutasDTO ciudadRutasDTO){
        List<RutaDTO> rutasOrigenDTO = getRutasOrigenCiudad(ciudadRutasDTO).get();
        List<Long> idsRutas = rutasOrigenDTO.stream()
                .map(RutaDTO::getId)
                .toList();
        List<RutaCiudadDTO> rutaCiudadDTOs = idsRutas.stream()
                .map(rutaService::getRutaCiudad)
                .toList().stream()
                .map(Optional<RutaCiudadDTO>::get)
                .toList();
        List<Long> idCiudadesDestino = rutaCiudadDTOs.stream()
                .map(RutaCiudadDTO::getCiudadDestinoId)
                .toList();
        List<CiudadDTO> ciudadesDestino = idCiudadesDestino.stream()
                .map(ciudadService::getCiudadById)
                .map(Optional::get)
                .toList();
        Set<String> nombresUnicos = new HashSet<>();
        List<CiudadDTO> ciudadesDestinoFiltradas = ciudadesDestino.stream()
            .filter(ciudad -> nombresUnicos.add(ciudad.getNombre()))
            .toList();
        return Optional.of(ciudadesDestinoFiltradas);
    }

    public Ciudad getCiudadDestinoByid(Long ciudadId) {
        return ciudadService.getCiudadById(ciudadId)
                .map(CiudadMapper::toEntity)
                .orElseThrow(() -> new IllegalArgumentException("Ciudad not found for id: " + ciudadId));
    }

    public Optional<List<RutaDTO>> getRutasCiudadAOtra(Long ciudadId, Long ciudadDestinoId) {
        CiudadRutasDTO ciudadRutasDTO = getCiudadRutas(ciudadId).get();
        List<RutaCiudadDTO> rutaCiudadDTOs = ciudadRutasDTO.getIdRutasOrigen()
                .stream()
                .map(rutaService::getRutaCiudad)
                .toList()
                .stream()
                .map(Optional::get)
                .toList();
        List<RutaCiudadDTO> rutaCiudadDTOsFiltrado = rutaCiudadDTOs.stream()
                .filter(x -> x.getCiudadOrigenId() == ciudadId && x.getCiudadDestinoId() == ciudadDestinoId)
                .toList();
        List<Long> idRutasFiltradas = rutaCiudadDTOsFiltrado.stream().map(RutaCiudadDTO::getRutaId).toList();
        return Optional.of(rutaService.listaIdstoRuta(idRutasFiltradas));
    }
}
