package co.edu.javeriana.caravana_medieval.init;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

    // Modificado para 100 ciudades
    private static final int NUM_CIUDADES = 100;
    private static final String[] NOMBRES_SERVICIOS = { "Reparar", "Mejorar capacidad", "Mejorar velocidad", "Guardias" };
    private static final String[] ROLES = { "Comerciante", "Caravanero", "Administrador" };

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Random random = new Random();

        // Crear 100 ciudades con nombres numerados
        List<Ciudad> ciudades = new ArrayList<>();
        for (int i = 1; i <= NUM_CIUDADES; i++) {
            Ciudad ciudad = ciudadRepository.save(new Ciudad("Casa de Henao calle #" + i, random.nextInt(10) + 5));
            ciudades.add(ciudad);
        }

        // Crear 50 productos con nombres numerados
        List<Producto> productos = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Producto producto = productoRepository.save(new Producto("Vitafer #" + i, i, "Multivitanimico para la salud #" + i));
            productos.add(producto);
        }

        // Asociar productos a ciudades (todas las ciudades tienen todos los productos)
        for (Ciudad ciudad : ciudades) {
            for (Producto producto : productos) {
                double factorDemanda = 0.5 + (random.nextDouble() * 1.5);
                double factorOferta = 0.5 + (random.nextDouble() * 1.5);
                ciudadProductoRepository.save(new CiudadProducto(ciudad, producto, factorDemanda, factorOferta));
            }
        }

        // Crear servicios (igual que antes)
        List<Servicio> servicios = new ArrayList<>();
        for (String nombre : NOMBRES_SERVICIOS) {
            servicios.add(servicioRepository.save(new Servicio(nombre, "Descripción de " + nombre)));
        }

        // Asociar servicios a ciudades
        for (Ciudad ciudad : ciudades) {
            for (Servicio servicio : servicios) {
                double precio = 50 + random.nextInt(100);
                ciudadServicioRepository.save(new CiudadServicio(ciudad, servicio, precio));
            }
        }

        // Crear 3 caravanas
        List<Caravana> caravanas = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            caravanas.add(caravanaRepository.save(new Caravana("Caravana" + i, 10 + i, 100 + i * 10, 500 + i * 100, 100)));
        }

        // Crear 10 jugadores (4 en la primera caravana, 3 en las demás)
        int jugadoresCreados = 0;
        for (Caravana caravana : caravanas) {
            int jugadoresPorCaravana = (caravana.getNombre().equals("Caravana1")) ? 4 : 3;
            for (int j = 0; j < jugadoresPorCaravana; j++) {
                jugadorRepository.save(new Jugador(ROLES[j % ROLES.length], "Jugador_" + (jugadoresCreados + 1), caravana));
                jugadoresCreados++;
            }
        }

        // Generar 100 rutas aleatorias únicas
        Set<String> rutasExistentes = new HashSet<String>();
        int rutasCreadas = 0;
        while (rutasCreadas < 100) {
            Ciudad origen = ciudades.get(random.nextInt(NUM_CIUDADES));
            Ciudad destino = ciudades.get(random.nextInt(NUM_CIUDADES));
            if (!origen.equals(destino) && !rutasExistentes.contains(origen.getId() + "-" + destino.getId())) {
                boolean segura = random.nextBoolean();
                int longitud = 20 + random.nextInt(30);
                int dano = segura ? 0 : 10 + random.nextInt(20); //la ruta depende de la longitud, revisar esto

                rutaRepository.save(new Ruta(origen, destino, longitud, dano));
                rutasExistentes.add(origen.getId() + "-" + destino.getId());
                rutasCreadas++;
            }
        }

        log.info("Base de datos inicializada con {} ciudades, {} productos, 10 jugadores y 100 rutas.", NUM_CIUDADES, productos.size());
    }
}
