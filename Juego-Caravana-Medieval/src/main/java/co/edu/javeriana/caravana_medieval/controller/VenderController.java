package co.edu.javeriana.caravana_medieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.ErrorDTO;
import co.edu.javeriana.caravana_medieval.dto.JugadorDTO;
import co.edu.javeriana.caravana_medieval.model.Role;
import co.edu.javeriana.caravana_medieval.service.JugadorService;
import co.edu.javeriana.caravana_medieval.service.JwtService;
import co.edu.javeriana.caravana_medieval.service.VenderService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/vender")
public class VenderController {
    @Autowired
    private VenderService venderService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private JugadorService jugadorService;

    JugadorDTO jugadorDTO;

    @Secured({ Role.Code.COMERCIANTE })
    @PutMapping("/productos")
    public ResponseEntity<?> venderProducto(
            @RequestBody CiudadProductoDTO ciudadProductoDTO,
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

        venderService.venderProducto(ciudadProductoDTO, jugadorDTO.getIdCaravana());
        return ResponseEntity.ok().build();
    }

}
