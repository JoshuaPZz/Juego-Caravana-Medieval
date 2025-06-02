package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadRutasDTO;
import co.edu.javeriana.caravana_medieval.dto.ErrorDTO;
import co.edu.javeriana.caravana_medieval.dto.JugadorDTO;
import co.edu.javeriana.caravana_medieval.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval.mapper.CaravanaMapper;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.service.CiudadRutaService;
import co.edu.javeriana.caravana_medieval.service.JugadorService;
import co.edu.javeriana.caravana_medieval.service.JwtService;
import co.edu.javeriana.caravana_medieval.service.RutaService;
import co.edu.javeriana.caravana_medieval.service.ViajeService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ciudad")
public class CiudadRutaController {
    
    @Autowired
    private RutaService rutaService;
    @Autowired
    private CiudadRutaService ciudadRutaService;
    @Autowired
    private JugadorService jugadorService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ViajeService viajeService;

    private JugadorDTO jugadorDTO;

    @GetMapping("/ciudadesorigen")
    public  ResponseEntity<?> getCiudadesRutaOrigen(HttpServletRequest request) {
         try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorDTO("Token no proporcionado o mal formado"));
            }
            String token = authHeader.substring(7);
            String username = jwtService.extractUserName(token);
            jugadorDTO = jugadorService.getJugadorbyEmail(username);
            CiudadDTO ciudadActual = viajeService.getCiudadActual(jugadorDTO.getIdCaravana());
            CiudadRutasDTO ciudadRutasDTO = ciudadRutaService.getCiudadRutas(ciudadActual.getId()).orElseThrow();
            List<CiudadDTO> ciudadesDestino = ciudadRutaService.getDestinosCiudad(ciudadRutasDTO).get();
            return ResponseEntity.ok(ciudadesDestino);
        }catch (Exception e) {
            System.err.println("Error al buscar las ciudades de destino " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(new ErrorDTO("Error interno del servidor: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}/rutasorigen")
    public List<RutaDTO> getRutasOrigen(@PathVariable("id") Long id) {
        CiudadRutasDTO ciudadRutasDTO = ciudadRutaService.getCiudadRutas(id).orElseThrow();
        return ciudadRutaService.getRutasOrigenCiudad(ciudadRutasDTO).get();
    }
    
    @GetMapping("/{id}/rutadestino")
    public List<RutaDTO> getRutasDestino(@PathVariable("id") Long id) {
        CiudadRutasDTO ciudadRutasDTO = ciudadRutaService.getCiudadRutas(id).orElseThrow();
        return rutaService.listaIdstoRuta(ciudadRutasDTO.getIdRutasDestino());
    }

    @GetMapping("/rutashacia/{idDestino}")
    public ResponseEntity<?> getRutasCiudadAOtra(HttpServletRequest request, @PathVariable("idDestino") Long idDestino) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorDTO("Token no proporcionado o mal formado"));
            }
            String token = authHeader.substring(7);
            String username = jwtService.extractUserName(token);
            jugadorDTO = jugadorService.getJugadorbyEmail(username);
            CiudadDTO ciudadActual = viajeService.getCiudadActual(jugadorDTO.getIdCaravana());
            List<RutaDTO> ciudadesDestino = ciudadRutaService.getRutasCiudadAOtra(ciudadActual.getId(), idDestino).get();
            return ResponseEntity.ok(ciudadesDestino);
        }catch (Exception e) {
            System.err.println("Error al buscar las rutas hacia el destino " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(new ErrorDTO("Error interno del servidor: " + e.getMessage()));
        }
    }
    
}
