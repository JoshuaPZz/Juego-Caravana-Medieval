package co.edu.javeriana.caravana_medieval.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.ErrorDTO;
import co.edu.javeriana.caravana_medieval.service.ViajeService;

@RestController

@RequestMapping("/viaje")

public class ViajeController {
    @Autowired
    private ViajeService viajeService;

    @GetMapping("/ciudadActual/{id}")
    public CiudadDTO getCiudadActual(@PathVariable Long id) {
        return viajeService.getCiudadActual(id);
    }

    @PutMapping("/{idCaravana}/{idCiudadDestino}/{idRuta}")
    public ResponseEntity<?> viajar(
            @PathVariable Long idCaravana,
            @PathVariable Long idCiudadDestino,
            @PathVariable Long idRuta) {
        try {
            CaravanaDTO caravanaDTO = viajeService.viajar(idCaravana, idCiudadDestino, idRuta);
            System.out.println("Viajando a..." + idCiudadDestino);
            return ResponseEntity.ok(caravanaDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorDTO("Error interno del servidor"));
        }
    }
}
