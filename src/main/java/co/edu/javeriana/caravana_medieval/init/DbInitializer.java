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

    @Autowired
    private ServicioCompraRepository servicioCompraRepository;

    private Logger log = LoggerFactory.getLogger(getClass());

    private static final int NUM_CIUDADES = 100;
    private static final int NUM_PRODUCTOS = 50;
    private static final String[] NOMBRES_SERVICIOS = { "Reparar", "Mejorar capacidad", "Mejorar velocidad", "Guardias" };
    private static final String[] ROLES = { "Comerciante", "Caravanero", "Administrador" };

    @Override
    @Transactional
    public void run(String... args) throws Exception {
         if (ciudadRepository.count() == 0) {
        Random random = new Random();

        // Crear 100 ciudades con nombres numerados
        List<Ciudad> ciudades = new ArrayList<>();
        for (int i = 1; i <= NUM_CIUDADES; i++) {
            Ciudad ciudad = ciudadRepository.save(new Ciudad("Casa de Henao calle #" + i, random.nextInt(10) + 5));
            ciudades.add(ciudad);
        }

        // Crear 50 productos con nombres numerados
        List<Producto> productos = new ArrayList<>();
        for (int i = 1; i <= NUM_PRODUCTOS; i++) {
            Producto producto = productoRepository.save(new Producto("Vitafer #" + i, i, "Multivitamínico para la salud #" + i));
            productos.add(producto);
        }

        // Asociar productos a ciudades y calcular precios de venta (PV) y compra (PC)
        for (Ciudad ciudad : ciudades) {
    int numProductosPorCiudad = 5 + random.nextInt(16); // Entre 5 y 20 productos por ciudad
    Set<Producto> productosSeleccionados = new HashSet<>();
    while (productosSeleccionados.size() < numProductosPorCiudad) {
        int index = random.nextInt(productos.size());
        Producto producto = productos.get(index);
        productosSeleccionados.add(producto);
    }
    for (Producto producto : productosSeleccionados) {
        double factorDemanda = 0.5 + (random.nextDouble() * 1.5);
        double factorOferta = 0.5 + (random.nextDouble() * 1.5);
        int stock = random.nextInt(50) + 1;

        CiudadProducto ciudadProducto = new CiudadProducto(ciudad, producto, factorDemanda, factorOferta, stock);
        ciudadProductoRepository.save(ciudadProducto);
    }
}

        // Crear servicios
        List<Servicio> servicios = new ArrayList<>();
        for (String nombre : NOMBRES_SERVICIOS) {
            servicios.add(servicioRepository.save(new Servicio(nombre, "Descripción de " + nombre)));
        }

        // Asociar servicios a ciudades
        
        for (Ciudad ciudad : ciudades) {
            List<CiudadServicio> serviciosDeLaCiudad = new ArrayList<>();
            for (Servicio servicio : servicios) {
                double precio = 50 + random.nextInt(100);
                CiudadServicio ciudadServicio = new CiudadServicio(ciudad, servicio, precio);
                serviciosDeLaCiudad.add(ciudadServicio);
                ciudadServicioRepository.save(ciudadServicio);
            }
            ciudad.setServicios(serviciosDeLaCiudad);
        }

        // Crear 3 caravanas
        List<Caravana> caravanas = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            caravanas.add(caravanaRepository.save(new Caravana("Caravana" + i, 10 + i, 100 + i * 10, 500 + i * 100, 100)));
        }

        // Crear 10 jugadores
        int jugadoresCreados = 0;
        for (Caravana caravana : caravanas) {
            int jugadoresPorCaravana = (caravana.getNombre().equals("Caravana1")) ? 4 : 3;
            for (int j = 0; j < jugadoresPorCaravana; j++) {
                jugadorRepository.save(new Jugador(ROLES[j % ROLES.length], "Jugador_" + (jugadoresCreados + 1), caravana));
                jugadoresCreados++;
            }
        }

        // Crear las compras
        for(Caravana caravana : caravanas){
            for(Ciudad ciudad : ciudades){
                List<CiudadServicio> serviciosCiudad = ciudad.getServicios();
                if (serviciosCiudad != null && !serviciosCiudad.isEmpty()) {
                    int indexRandom = random.nextInt(serviciosCiudad.size());
                    ServicioCompra servicioCompra = new ServicioCompra(caravana, ciudad, serviciosCiudad.get(indexRandom).getServicio());
                    ciudad.getCompras().add(servicioCompra);
                    servicioCompraRepository.save(servicioCompra);
                }                
            }
        }

        // Asegurar que cada ciudad tenga al menos 6 rutas de origen y 6 de destino
        for (Ciudad ciudad : ciudades) {
            Set<Ciudad> destinos = new HashSet<>();
            Set<Ciudad> origenes = new HashSet<>();

            while (destinos.size() < 6) {
                Ciudad destino = ciudades.get(random.nextInt(NUM_CIUDADES));
                if (!ciudad.equals(destino) && !destinos.contains(destino)) {
                    int longitud = 10 + random.nextInt(131); // Rutas entre 10 y 140
                    int dano = calcularDanoRuta(longitud, caravanas);

                    rutaRepository.save(new Ruta(ciudad, destino, longitud, dano));
                    destinos.add(destino);
                }
            }

            while (origenes.size() < 6) {
                Ciudad origen = ciudades.get(random.nextInt(NUM_CIUDADES));
                if (!ciudad.equals(origen) && !origenes.contains(origen)) {
                    int longitud = 10 + random.nextInt(131);
                    int dano = calcularDanoRuta(longitud, caravanas);

                    rutaRepository.save(new Ruta(origen, ciudad, longitud, dano));
                    origenes.add(origen);
                }
            }
        }

        // Generar rutas adicionales hasta completar 100
        Set<String> rutasExistentes = new HashSet<>();
        long rutasCreadas = rutaRepository.count(); // Contar las rutas ya creadas

        while (rutasCreadas < 100) {
            Ciudad origen = ciudades.get(random.nextInt(NUM_CIUDADES));
            Ciudad destino = ciudades.get(random.nextInt(NUM_CIUDADES));

            if (!origen.equals(destino) && !rutasExistentes.contains(origen.getId() + "-" + destino.getId())) {
                int longitud = 10 + random.nextInt(131);
                int dano = calcularDanoRuta(longitud, caravanas);

                rutaRepository.save(new Ruta(origen, destino, longitud, dano));
                rutasExistentes.add(origen.getId() + "-" + destino.getId());
                rutasCreadas++;
            }
        }

        log.info("Base de datos inicializada con {} ciudades, {} productos, 10 jugadores y 100 rutas.", NUM_CIUDADES, productos.size());
        log.info("Base de datos inicializada desde cero");
    } else {
        log.info("La base de datos ya contiene informacion - omitiendo inicializacion");
    }
    }

    /**
     * Calcula el daño de la ruta basado en la distancia y la vida de la caravana.
     * Cuanto más larga sea la ruta, más daño causará.
     */
    private int calcularDanoRuta(int longitud, List<Caravana> caravanas) {
        int vidaMaximaCaravana = caravanas.stream().mapToInt(Caravana::getPuntosVida).max().orElse(100);
        double factorDano = (double) longitud / 140; // Escala el daño basado en la longitud (máximo 140)
        return (int) (factorDano * vidaMaximaCaravana * 0.3); // Máximo 30% de la vida de la caravana
    }
}
