package co.edu.javeriana.caravana_medieval.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.edu.javeriana.caravana_medieval.model.*;
import co.edu.javeriana.caravana_medieval.repository.*;
import jakarta.transaction.Transactional;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CiudadProductoRepository ciudadProductoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private CiudadServicioRepository ciudadServicioRepository;

    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private RutaRepository rutaRepository;

    private Logger log = LoggerFactory.getLogger(getClass());

    private static final String[] NOMBRES_CIUDADES = { "Anor Londo", "Leyndell", "Raya Lucaria", "Irithyll", "Casa de Henao" };
    private static final String[] NOMBRES_PRODUCTOS = { "Especias", "Telas", "Armas", "Metales Preciosos", "Ganado" };
    private static final String[] NOMBRES_SERVICIOS = { "Reparar", "Mejorar capacidad", "Mejorar velocidad", "Guardias" };
    private static final String[] ROLES = { "Comerciante", "Caravanero", "Administrador" };

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Random random = new Random();

        // Crear ciudades
        List<Ciudad> ciudades = new ArrayList<>();
        for (String nombre : NOMBRES_CIUDADES) {
            Ciudad ciudad = ciudadRepository.save(new Ciudad(nombre, random.nextInt(10) + 5)); // Impuesto aleatorio entre 5 y 15 MO
            ciudades.add(ciudad);
            //log.info("Ciudad creada: {} con impuesto {}", ciudad.getNombre(), ciudad.getImpuesto());
        }

        // Crear productos
        List<Producto> productos = new ArrayList<>();
        for (int i = 0; i < NOMBRES_PRODUCTOS.length; i++) {
            Producto producto = productoRepository.save(new Producto(NOMBRES_PRODUCTOS[i], i, "Descripción de " + NOMBRES_PRODUCTOS[i]));
            productos.add(producto);
        }

        // Asociar productos a ciudades con stock y factores aleatorios
        for (Ciudad ciudad : ciudades) {
            for (Producto producto : productos) {
                double factorDemanda = 0.5 + (random.nextDouble() * 1.5); // Factor de demanda entre 0.5 y 2.0
                double factorOferta = 0.5 + (random.nextDouble() * 1.5); // Factor de oferta entre 0.5 y 2.0
                CiudadProducto ciudadProducto = ciudadProductoRepository.save(new CiudadProducto(ciudad, producto, factorDemanda, factorOferta));
                //log.info("Ciudad {} comercia {} con FD={} y FO={}", ciudad.getNombre(), producto.getNombre(), factorDemanda, factorOferta);
            }
        }

        // Crear servicios
        List<Servicio> servicios = new ArrayList<>();
        for (String nombre : NOMBRES_SERVICIOS) {
            Servicio servicio = servicioRepository.save(new Servicio(nombre, "Descripción de " + nombre));
            servicios.add(servicio);
        }

        // Asociar servicios a ciudades con precios aleatorios
        for (Ciudad ciudad : ciudades) {
            for (Servicio servicio : servicios) {
                double precio = 50 + random.nextInt(100); // Precio entre 50 y 150 MO
                CiudadServicio ciudadServicio = ciudadServicioRepository.save(new CiudadServicio(ciudad, servicio, precio));
                //log.info("Ciudad {} ofrece servicio {} por {} MO", ciudad.getNombre(), servicio.getNombre(), precio);
            }
        }

        // Crear caravanas
        List<Caravana> caravanas = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Caravana caravana = caravanaRepository.save(new Caravana("Caravana" + i, 10 + i, 100 + i * 10, 500 + i * 100, 100));
            caravanas.add(caravana);
            //log.info("Caravana creada: {} con {} MO disponibles", caravana.getNombre(), caravana.getDineroDisponible());
        }

        // Crear jugadores y asignarlos a caravanas con diferentes roles
        for (Caravana caravana : caravanas) {
            for (int j = 0; j < ROLES.length; j++) {
                Jugador jugador = jugadorRepository.save(new Jugador(ROLES[j], "Jugador_" + j, caravana));
                //log.info("Jugador creado: {} con rol {} en {}", jugador.getNombre(), jugador.getRol(), caravana.getNombre());
            }
        }

        // Crear rutas entre ciudades (alternando seguras e inseguras)
        for (int i = 0; i < ciudades.size() - 1; i++) {
            boolean segura = random.nextBoolean();
            int longitud = 20 + random.nextInt(30); // Longitud entre 20 y 50
            int dano = segura ? 0 : 10 + random.nextInt(20); // Daño entre 10 y 30 si es insegura

            Ruta ruta = rutaRepository.save(new Ruta(ciudades.get(i), ciudades.get(i + 1), longitud, dano));
            //log.info("Ruta creada de {} a {} ({}segura) - Longitud: {}, Daño: {}", ciudades.get(i).getNombre(), ciudades.get(i + 1).getNombre(), segura ? "" : "In", longitud, dano);
        }

        log.info("Base de datos inicializada correctamente.");
    }
}
