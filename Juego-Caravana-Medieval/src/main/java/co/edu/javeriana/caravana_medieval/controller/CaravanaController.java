package co.edu.javeriana.caravana_medieval.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.ErrorDTO;
import co.edu.javeriana.caravana_medieval.dto.JugadorDTO;
import co.edu.javeriana.caravana_medieval.dto.ProductoDTO;
import co.edu.javeriana.caravana_medieval.mapper.CaravanaMapper;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.service.CaravanaService;
import co.edu.javeriana.caravana_medieval.service.JugadorService;
import co.edu.javeriana.caravana_medieval.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/caravana")
public class CaravanaController {
    @Autowired
    private CaravanaService caravanaService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private JugadorService jugadorService;

    private JugadorDTO jugadorDTO;

    @GetMapping("/actual")
    public ResponseEntity<?> getCaravanaById(HttpServletRequest request) {
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
            
        Caravana caravana = caravanaService.getCaravanaById(jugadorDTO.getIdCaravana());
        CaravanaDTO caravanaDTO = CaravanaMapper.toDTO(caravana);
        return ResponseEntity.ok(caravanaDTO);

        }catch (Exception e) {
            System.err.println("Error al buscar la caravana del jugador " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(new ErrorDTO("Error interno del servidor: " + e.getMessage()));
        }
        
    }

    @GetMapping("/productos")
    public ResponseEntity<?> getCaravanaProductoById(HttpServletRequest request) {
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
            List<ProductoDTO> productoDTO = caravanaService.getCaravanaProductoById(jugadorDTO.getIdCaravana());
            return ResponseEntity.ok(productoDTO);
        }catch (Exception e) {
            System.err.println("Error al buscar los productos de la caravana " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(new ErrorDTO("Error interno del servidor: " + e.getMessage()));
        }
    }

    @GetMapping("/caravanaproductos")
    public ResponseEntity<?> getCaravanaProductoDTOs(HttpServletRequest request){
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
            List<CaravanaProductoDTO> caravanaProducto = caravanaService.getCaravanaProductoDTOs(jugadorDTO.getIdCaravana());
            return ResponseEntity.ok(caravanaProducto);
        }catch (Exception e) {
            System.err.println("Error al buscar los Caravana/Producto " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(new ErrorDTO("Error interno del servidor: " + e.getMessage()));
        }
    }

    @PutMapping("/nuevojuego")
    public CaravanaDTO nuevoJuego(){
        return caravanaService.reiniciarCaravana();
    }

}
