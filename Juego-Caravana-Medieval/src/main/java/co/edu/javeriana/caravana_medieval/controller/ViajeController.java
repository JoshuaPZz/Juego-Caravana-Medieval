package co.edu.javeriana.caravana_medieval.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.ErrorDTO;
import co.edu.javeriana.caravana_medieval.dto.JugadorDTO;
import co.edu.javeriana.caravana_medieval.model.Role;
import co.edu.javeriana.caravana_medieval.service.JugadorService;
import co.edu.javeriana.caravana_medieval.service.JwtService;
import co.edu.javeriana.caravana_medieval.service.ViajeService;
import jakarta.servlet.http.HttpServletRequest;

@RestController

@RequestMapping("/viaje")

public class ViajeController {
    @Autowired
    private ViajeService viajeService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private JugadorService jugadorService;

    private JugadorDTO jugadorDTO;

    @GetMapping("/ciudadActual")
    public ResponseEntity<?> getCiudadActual(HttpServletRequest request) {
         try {

            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorDTO("Token no proporcionado o mal formado"));
            }
            // 4) Extrae el token y el username
            String token = authHeader.substring(7);
            String username = jwtService.extractUserName(token);

            jugadorDTO = jugadorService.getJugadorbyEmail(username);
            CiudadDTO ciudadActual = viajeService.getCiudadActual(jugadorDTO.getIdCaravana());
            return ResponseEntity.ok(ciudadActual);
        } catch (Exception e) {
            System.err.println("Error al buscar la ciudad actual de la caravana " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(new ErrorDTO("Error interno del servidor: " + e.getMessage()));
        }
    }

    @Secured({ Role.Code.CARAVANERO })
    @PutMapping("/{idCiudadDestino}/{idRuta}")
    public ResponseEntity<?> viajar(
            // @PathVariable Long idCaravana,
            @PathVariable Long idCiudadDestino,
            @PathVariable Long idRuta,
            HttpServletRequest request) {
        try {

            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorDTO("Token no proporcionado o mal formado"));
            }

            // 4) Extrae el token y el username
            String token = authHeader.substring(7);
            String username = jwtService.extractUserName(token);

            jugadorDTO = jugadorService.getJugadorbyEmail(username);

            System.out.println(
                    "Iniciando viaje: caravana=" + jugadorDTO.getIdCaravana() + ", destino=" + idCiudadDestino
                            + ", ruta=" + idRuta);
            CaravanaDTO caravanaDTO = viajeService.viajar(jugadorDTO.getIdCaravana(), idCiudadDestino, idRuta);
            System.out.println("Viaje completado con éxito");
            return ResponseEntity.ok(caravanaDTO);
        } catch (Exception e) {
            System.err.println("Error durante el viaje: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(new ErrorDTO("Error interno del servidor: " + e.getMessage()));
        }
    }
}
