package co.edu.javeriana.caravana_medieval.controller;

import co.edu.javeriana.caravana_medieval.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval.dto.JwtAuthenticationResponse;
import co.edu.javeriana.caravana_medieval.dto.LoginDTO;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.model.Jugador;
import co.edu.javeriana.caravana_medieval.model.Role;
import co.edu.javeriana.caravana_medieval.model.Ruta;
import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.repository.JugadorRepository;
import co.edu.javeriana.caravana_medieval.repository.RutaRepository;
import co.edu.javeriana.caravana_medieval.service.JwtService;
import co.edu.javeriana.caravana_medieval.service.AuthenticationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("integration-testing")
public class ViajeControllerIntegrationTest {

    private String SERVER_URL;

    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired(required = false)
    private JwtService jwtService;

    @Autowired(required = false)
    private AuthenticationService authService;

    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;
    private Caravana caravana;
    private Ruta ruta;
    private Jugador jugador;

    @Autowired
    private TestRestTemplate rest;

    public ViajeControllerIntegrationTest(@Value("${server.port}") int serverPort) {
        this.SERVER_URL = "http://localhost:" + serverPort + "/";
    }

    @BeforeEach
    void init() {
        // Crear ciudades
        ciudadOrigen = new Ciudad("CiudadOrigen", 0);
        ciudadDestino = new Ciudad("CiudadDestino", 10);
        ciudadOrigen.setRutasOrigen(new ArrayList<>());
        ciudadDestino.setRutasDestino(new ArrayList<>());
        ciudadRepository.save(ciudadOrigen);
        ciudadRepository.save(ciudadDestino);

        // Crear ruta
        ruta = new Ruta(100, 20); // longitud = 100, daño = 20
        rutaRepository.save(ruta);

        // Conectar la ruta a las ciudades
        ciudadOrigen.getRutasOrigen().add(ruta);
        ciudadDestino.getRutasDestino().add(ruta);
        ciudadRepository.save(ciudadOrigen);
        ciudadRepository.save(ciudadDestino);

        // Crear caravana
        caravana = new Caravana("CaravanaPrueba", 10, 100, 500, 200, ciudadOrigen, null, false);
        caravana.setHoraViaje(java.time.LocalTime.of(10, 0));
        caravanaRepository.save(caravana);

        // Crear jugador con contraseña encriptada si es necesario
        jugador = new Jugador("Jugador1", "jugador1@ola.com", "jugador1", Role.CARAVANERO, caravana);
        jugadorRepository.save(jugador);
    }

    private String getValidToken() {
        // Estrategia 1: Usar el servicio directamente si está disponible
        if (authService != null) {
            try {
                JwtAuthenticationResponse response = authService.login(new LoginDTO("jugador1@ola.com", "jugador1"));
                return response.getToken();
            } catch (Exception e) {
                System.out.println("Error al usar AuthService directamente: " + e.getMessage());
            }
        }

        // Estrategia 2: Usar el JwtService directamente si está disponible
        if (jwtService != null) {
            try {
                return jwtService.generateToken(jugador.getUsername());
            } catch (Exception e) {
                System.out.println("Error al generar token con JwtService: " + e.getMessage());
            }
        }

        // Estrategia 3: Usar el endpoint REST
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            LoginDTO loginRequest = new LoginDTO("jugador1@ola.com", "jugador1");
            HttpEntity<LoginDTO> request = new HttpEntity<>(loginRequest, headers);

            ResponseEntity<JwtAuthenticationResponse> response = rest.exchange(
                    SERVER_URL + "auth/login",
                    HttpMethod.POST,
                    request,
                    JwtAuthenticationResponse.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody().getToken();
            } else {
                // Imprimir detalles del error para depuración
                System.out.println("Error en login REST: " + response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Excepción en login REST: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Test
    void viajar() {
        System.out.println("Iniciando prueba de viaje");

        // Obtener token válido
        String token = getValidToken();
        assertNotNull(token, "No se pudo obtener un token válido para las pruebas");
        System.out.println("Token obtenido: " + token.substring(0, 10) + "...");

        // Configurar headers para la petición
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        System.out.println("Headers configurados correctamente");

        jugador.getCaravana();

        // Realizar la petición al endpoint de viaje
        System.out.println("Enviando petición a: " + SERVER_URL + "viaje/" + caravana.getId() + "/"
                + ciudadDestino.getId() + "/" + ruta.getId());

        webTestClient.put()
                .uri(SERVER_URL + "viaje/{idCiudadDestino}/{idRuta}",
                        ciudadDestino.getId(),
                        ruta.getId())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CaravanaDTO.class)
                .consumeWith(response -> {
                    System.out.println("Respuesta recibida del servidor");
                    CaravanaDTO caravanaDTO = response.getResponseBody();
                    assertNotNull(caravanaDTO, "La respuesta no debe ser nula");
                    System.out.println("Datos de la caravana: " + caravanaDTO.getNombre() + ", PV: "
                            + caravanaDTO.getPuntosVida());
                    assertEquals("CaravanaPrueba", caravanaDTO.getNombre(), "El nombre de la caravana debe coincidir");
                    assertEquals(180, caravanaDTO.getPuntosVida(),
                            "Los puntos de vida deben ser correctos después del viaje");
                    assert caravanaDTO.getNombre().equals("CaravanaPrueba");
                    assert caravanaDTO.getPuntosVida() == 180;
                    System.out.println("Todas las aserciones pasaron correctamente");
                });

        System.out.println("Prueba de viaje completada con éxito");
    }
}