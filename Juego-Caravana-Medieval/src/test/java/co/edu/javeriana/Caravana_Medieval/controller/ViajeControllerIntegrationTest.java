package co.edu.javeriana.Caravana_Medieval.controller;

import co.edu.javeriana.caravana_medieval.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.model.Ruta;
import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval.repository.RutaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashSet;
import java.util.ArrayList;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("integration-testing")
public class ViajeControllerIntegrationTest {

    private  String SERVER_URL;

    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private WebTestClient webTestClient;

    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;
    private Caravana caravana;
    private Ruta ruta;

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
    }

    @Test
    void viajar() {
        webTestClient.put()
        .uri(SERVER_URL + "viaje/{idCaravana}/{idCiudadDestino}/{idRuta}", 
            caravana.getId(), 
            ciudadDestino.getId(), 
            ruta.getId())
        .exchange()
        .expectStatus().isOk()
        .expectBody(CaravanaDTO.class)
        .consumeWith(response -> {
            CaravanaDTO caravanaDTO = response.getResponseBody();
            assert caravanaDTO != null;
            assert caravanaDTO.getNombre().equals("CaravanaPrueba");
            assert caravanaDTO.getPuntosVida() == 180;
        });
}
}
