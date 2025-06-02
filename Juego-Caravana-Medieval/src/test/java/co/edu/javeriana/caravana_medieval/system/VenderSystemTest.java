package co.edu.javeriana.caravana_medieval.system;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

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
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("system-testing")
public class VenderSystemTest {

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
    @Autowired
    private PasswordEncoder passwordEncoder;


    private Jugador jugador;
    private Caravana caravana;
    private Ciudad ciudad;
    private Producto producto;
    private CaravanaProducto caravanaProducto;
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    
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
        String password = "Jugador2";
        String passEncoded = passwordEncoder.encode(password);
        jugador = new Jugador("Jugador2", "jugador2@ola.com", passEncoded, Role.COMERCIANTE, caravana);
        jugadorRepository.save(jugador);
        this.playwright = Playwright.create();
        this.browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(false));
        this.browserContext = browser.newContext();
        this.page = browserContext.newPage();
        this.SERVER_URL = "http://localhost:4200";
    }
    @AfterEach
    void end() {
        browser.close();
        playwright.close();
    }

    @Test
    void venderProducto() {
        String cantidadVenta = "5";
        String stockRestante = "5";
        page.navigate(SERVER_URL);
        page.locator("input[name='email']").fill(jugador.getEmail()); 
        page.locator("input[name='password']").fill("Jugador2");
        Locator loginButton = page.locator("button[type='submit']");
        loginButton.waitFor();
        loginButton.click();
        page.locator("#playBtn").click();
        page.locator("#venderBtn").click();
        page.locator("//table//tr[1]//input").fill(cantidadVenta);
        page.locator("//table//tr[1]//button").click();
        Locator mensaje = page.locator("//app-popup//p");
        mensaje.waitFor();
        PlaywrightAssertions.assertThat(mensaje).hasText("Producto vendido con éxito.");
        page.reload();
        Locator valorFinal = page.locator("//table//tr[1]//td[5]");
        PlaywrightAssertions.assertThat(valorFinal).hasText(stockRestante);
    } 

    @Test
    void venderProductoExtraStock() {
        String cantidadVenta = "11";
        page.navigate(SERVER_URL);
        page.locator("input[name='email']").fill(jugador.getEmail()); 
        page.locator("input[name='password']").fill("Jugador2");
        Locator loginButton = page.locator("button[type='submit']");
        loginButton.waitFor();
        loginButton.click();
        page.locator("#playBtn").click();
        page.locator("#venderBtn").click();
        page.locator("//table//tr[1]//input").fill(cantidadVenta);
        page.locator("//table//tr[1]//button").click();
        Locator mensaje = page.locator("//app-popup//p");
        mensaje.waitFor();
        PlaywrightAssertions.assertThat(mensaje).hasText("La caravana no tiene la cantidad suficiente de productos para vender.");
    }

}
