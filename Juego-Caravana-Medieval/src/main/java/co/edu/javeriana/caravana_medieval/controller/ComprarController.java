package co.edu.javeriana.caravana_medieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.ErrorDTO;
import co.edu.javeriana.caravana_medieval.dto.JugadorDTO;
import co.edu.javeriana.caravana_medieval.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval.model.Role;
import co.edu.javeriana.caravana_medieval.service.ComprarService;
import co.edu.javeriana.caravana_medieval.service.JugadorService;
import co.edu.javeriana.caravana_medieval.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/comprar")
public class ComprarController {
    @Autowired
    private ComprarService comprarService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private JugadorService jugadorService;

    JugadorDTO jugadorDTO;

    @Secured({ Role.Code.COMERCIANTE })
    @PutMapping("/servicios/{idServicio}")
    public ResponseEntity<?> comprarServicio(@PathVariable Long idServicio,
            HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorDTO("Token no proporcionado o mal formado"));
        }

        // 4) Extrae el token y el username
        String token = authHeader.substring(7);
        String username = jwtService.extractUserName(token);

        jugadorDTO = jugadorService.getJugadorbyEmail(username);

        comprarService.comprarServicio(jugadorDTO.getIdCaravana(), idServicio);
        return ResponseEntity.ok().build();
    }

    @Secured({ Role.Code.COMERCIANTE })
    @PutMapping("productos")
    public ResponseEntity<?> comprarProducto(
            @RequestBody CaravanaProductoDTO caravanaProductoDTO,
            HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorDTO("Token no proporcionado o mal formado"));
        }

        // 4) Extrae el token y el username
        String token = authHeader.substring(7);
        String username = jwtService.extractUserName(token);

        jugadorDTO = jugadorService.getJugadorbyEmail(username);

        comprarService.comprarProducto(caravanaProductoDTO, jugadorDTO.getIdCaravana());
        return ResponseEntity.ok().build();
    }
}
