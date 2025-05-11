package co.edu.javeriana.Caravana_Medieval.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import co.edu.javeriana.caravana_medieval.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval.dto.JwtAuthenticationResponse;
import co.edu.javeriana.caravana_medieval.dto.LoginDTO;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval.model.Jugador;
import co.edu.javeriana.caravana_medieval.model.Producto;
import co.edu.javeriana.caravana_medieval.model.Role;
import co.edu.javeriana.caravana_medieval.repository.CaravanaProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadProductoRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.repository.JugadorRepository;
import co.edu.javeriana.caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval.service.AuthenticationService;
import co.edu.javeriana.caravana_medieval.service.JwtService;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("integration-testing")
public class VenderControllerIntegrationTest {

    private String SERVER_URL;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CaravanaRepository caravanaRepository;
    @Autowired
    private CiudadProductoRepository ciudadProductoRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CaravanaProductoRepository caravanaProductoRepository;
    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired(required = false)
    private JwtService jwtService;
    @Autowired(required = false)
    private AuthenticationService authService;
    @Autowired
    private TestRestTemplate rest;

    private Jugador jugador;
    private Caravana caravana;
    private Ciudad ciudad;
    private Producto producto;
    private CaravanaProducto caravanaProducto;

    public VenderControllerIntegrationTest(@Value("${server.port}") int serverPort) {
        this.SERVER_URL = "http://localhost:" + serverPort + "/";
    }

    @BeforeEach
    void init() {
        ciudad = new Ciudad("CiudadOrigen", 0);
        ciudadRepository.save(ciudad);

        producto = new Producto("ProductoPrueba", 2, "Flores de la region");
        productoRepository.save(producto);
        ciudadProductoRepository.save(new CiudadProducto(ciudad, producto, 23, 22, 100));

        caravana = new Caravana("CaravanaPrueba", 10, 100, 500, 200, ciudad, null, false);
        caravana.setHoraViaje(java.time.LocalTime.of(10, 0));
        caravanaRepository.save(caravana);

        caravanaProducto = new CaravanaProducto(caravana, producto, 10);
        caravanaProductoRepository.save(caravanaProducto);

        jugador = new Jugador("Jugador2", "jugador2@ola.com", "jugador2", Role.COMERCIANTE, caravana);
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

            LoginDTO loginRequest = new LoginDTO("jugador2@ola.com", "jugador2");
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
    void venderProducto() {
        System.out.println("Iniciando prueba de vender");
        // Obtener token válido
        String token = getValidToken();
        assertNotNull(token);
        System.out.println("Token obtenido: " + token.substring(0, 10) + "...");

        // Preparar DTO de venta
        CiudadProductoDTO dto = new CiudadProductoDTO();
        dto.setIdProducto(producto.getId());
        dto.setStock(5);

        // Configurar headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        System.out.println("Headers configurados correctamente");

        // Ejecutar petición y validar respuesta
        webTestClient.put()
                .uri(SERVER_URL + "vender/productos")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        // Verificar caravana en BD
        Caravana updatedCaravana = caravanaRepository.findById(caravana.getId()).orElseThrow();
        assertThat(updatedCaravana.getCapacidadMax()).isEqualTo(100);

        // Verificar CaravanaProducto en BD
        CaravanaProducto cp = caravanaProductoRepository
                .findByCaravanaIdAndProductoId(caravana.getId(), producto.getId())
                .orElseThrow();
        assertThat(cp.getCantidad()).isEqualTo(5);

        System.out.println("Prueba de venta completada con éxito");
    }

}
