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

    private static final String[] NOMBRES_SERVICIOS = {
            "Reparar", "Mejorar capacidad", "Mejorar velocidad", "Guardias"
    };

    private static final String[] DESCRIPCIONES_SERVICIOS = {
            "Repara el daño sufrido por la caravana.",
            "Aumenta la capacidad máxima de carga de la caravana.",
            "Mejora la velocidad de desplazamiento de la caravana.",
            "Reduce el daño recibido en rutas inseguras en un 25%."
    };

    private static final String[] ROLES = { "Comerciante", "Caravanero" };

    private static final String[] NOMBRES_PRODUCTOS = {
            "Especias", "Telas", "Armas", "Metales preciosos", "Ganado",
            "Piedras preciosas", "Hierbas medicinales", "Cerámica", "Vino", "Granos"
    };

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (ciudadRepository.count() == 0) {
            Random random = new Random();

            // Crear productos con nombres temáticos
            List<Producto> productos = new ArrayList<>();
            for (int i = 0; i < NUM_PRODUCTOS; i++) {
                String baseNombre = NOMBRES_PRODUCTOS[i % NOMBRES_PRODUCTOS.length];
                String nombre = baseNombre + " #" + (i + 1);
                String descripcion = "Producto valioso de tipo " + baseNombre.toLowerCase()
                        + " para comercio regional #" + (i + 1);
                Producto producto = productoRepository.save(new Producto(nombre, i + 1, descripcion));
                productos.add(producto);
            }

            // Crear ciudades con nombres más realistas
            List<Ciudad> ciudades = new ArrayList<>();
            for (int i = 1; i <= NUM_CIUDADES; i++) {
                Ciudad ciudad = ciudadRepository.save(new Ciudad("Ciudad #" + i, random.nextInt(20) + 1));
                ciudades.add(ciudad);
            }

            // Asociar productos a ciudades con factores de oferta/demanda y stock más
            // realistas
            for (Ciudad ciudad : ciudades) {
                int numProductosPorCiudad = 5 + random.nextInt(11); // Entre 5 y 15 productos por ciudad
                Set<Producto> productosSeleccionados = new HashSet<>();
                while (productosSeleccionados.size() < numProductosPorCiudad) {
                    int index = random.nextInt(productos.size());
                    Producto producto = productos.get(index);
                    productosSeleccionados.add(producto);
                }
                for (Producto producto : productosSeleccionados) {
                    double factorDemanda = 0.5 + (random.nextDouble() * 2.0); 
                    double factorOferta = 0.5 + (random.nextDouble() * 2.0); 
                    int stock = random.nextInt(100) + 1;
                    double precioBase = 5.0 + random.nextDouble() * 10.0; 
                    double precioCompra = precioBase * factorOferta;
                    double precioVenta = precioBase * factorDemanda;
                    precioCompra = Math.max(5.0, precioCompra);
                    precioVenta = Math.max(5.0, precioVenta);

                    CiudadProducto ciudadProducto = new CiudadProducto(ciudad, producto, factorDemanda, factorOferta, stock);
                    ciudadProducto.setPrecioCompra(precioCompra);
                    ciudadProducto.setPrecioVenta(precioVenta);
                    ciudadProductoRepository.save(ciudadProducto);
                }
            }

            // Crear servicios con descripciones detalladas
            List<Servicio> servicios = new ArrayList<>();
            for (int i = 0; i < NOMBRES_SERVICIOS.length; i++) {
                servicios.add(servicioRepository.save(new Servicio(NOMBRES_SERVICIOS[i], DESCRIPCIONES_SERVICIOS[i])));
            }

            // Asociar servicios a ciudades con precios variados
            // MODIFICACIÓN: Cada ciudad solo tendrá algunos servicios aleatorios, no todos
            for (Ciudad ciudad : ciudades) {
                List<CiudadServicio> serviciosDeLaCiudad = new ArrayList<>();

                // Determinar cuántos servicios tendrá esta ciudad (entre 1 y el total
                // disponible)
                int numServiciosParaCiudad = 1 + random.nextInt(servicios.size());

                // Seleccionar servicios aleatorios para esta ciudad
                Set<Integer> indicesServiciosSeleccionados = new HashSet<>();
                while (indicesServiciosSeleccionados.size() < numServiciosParaCiudad) {
                    indicesServiciosSeleccionados.add(random.nextInt(servicios.size()));
                }

                // Agregar solo los servicios seleccionados
                for (Integer indice : indicesServiciosSeleccionados) {
                    Servicio servicio = servicios.get(indice);
                    double precio = 50 + random.nextInt(200); // Precios entre 50 y 250
                    CiudadServicio ciudadServicio = new CiudadServicio(ciudad, servicio, precio);
                    serviciosDeLaCiudad.add(ciudadServicio);
                    ciudadServicioRepository.save(ciudadServicio);
                }

                ciudad.setServicios(serviciosDeLaCiudad);
            }

            // Crear 3 caravanas
            List<Caravana> caravanas = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                double dineroDisponible = 500 + i * 100;
                caravanas.add(caravanaRepository.save(new Caravana("Caravana" + i, 10, 100,
                        dineroDisponible, 100, ciudades.get(0), java.time.LocalTime.of(8, 0), false)));
            }

            // Crear 10 jugadores
            int jugadoresCreados = 0;
            for (Caravana caravana : caravanas) {
                int jugadoresPorCaravana = (caravana.getNombre().equals("Caravana1")) ? 4 : 3;
                for (int j = 0; j < jugadoresPorCaravana; j++) {
                    jugadorRepository
                            .save(new Jugador(ROLES[j % ROLES.length], "Jugador_" + (++jugadoresCreados), caravana));
                }
            }

            // Asegurar al menos una ruta desde cada ciudad (todas actúan como origen al
            // menos una vez)
            int minRutasPorCiudad = 5;
            int maxRutasTotales = 100;
            int rutasGeneradas = 0;

            // Asegurar que cada ciudad tenga al menos 5 rutas de salida (a diferentes
            // destinos)
            // y que entre cada par de ciudades haya 1 o 2 rutas aleatorias
            for (Ciudad origen : ciudades) {
                Set<Ciudad> destinosUnicos = new HashSet<>();

                // Primero asegurar al menos 5 destinos diferentes
                while (destinosUnicos.size() < Math.min(5, ciudades.size() - 1)) {
                    Ciudad destino;
                    do {
                        destino = ciudades.get(random.nextInt(NUM_CIUDADES));
                    } while (origen.equals(destino) || destinosUnicos.contains(destino));

                    destinosUnicos.add(destino);

                    // Crear entre 1 y 2 rutas para este par
                    int numRutas = 1 + random.nextInt(3); // 1 o 3
                    for (int i = 0; i < numRutas; i++) {
                        int longitud = 10 + random.nextInt(131);
                        int dano = calcularDanoRuta(longitud, caravanas);

                        Ruta ruta = new Ruta(longitud, dano);
                        ruta.setOrigen(origen);
                        ruta.setDestino(destino);
                        rutaRepository.save(ruta);
                        rutasGeneradas++;
                    }
                }

                // Opcional: agregar rutas adicionales para algunas ciudades
                if (random.nextDouble() < 0.3) { // 30% de probabilidad de tener más rutas
                    int rutasExtra = random.nextInt(3); // 0, 1 o 2 rutas extra
                    for (int i = 0; i < rutasExtra; i++) {
                        Ciudad destino = ciudades.get(random.nextInt(NUM_CIUDADES));
                        if (!origen.equals(destino)) {
                            int longitud = 10 + random.nextInt(131);
                            int dano = calcularDanoRuta(longitud, caravanas);

                            Ruta ruta = new Ruta(longitud, dano);
                            ruta.setOrigen(origen);
                            ruta.setDestino(destino);
                            rutaRepository.save(ruta);
                            rutasGeneradas++;
                        }
                    }
                }
            }

            log.info("Base de datos inicializada con {} ciudades, {} productos, {} servicios y 100 rutas.",
                    NUM_CIUDADES, productos.size(), servicios.size());
        } else {
            log.info("La base de datos ya contiene información - omitiendo inicialización");
        }
    }

    /**
     * Calcula el daño de la ruta basado en la distancia y la vida de la caravana.
     * Cuanto más larga sea la ruta, más daño causará.
     */
    private int calcularDanoRuta(int longitud, List<Caravana> caravanas) {
    int vidaMaximaCaravana = caravanas.stream().mapToInt(Caravana::getPuntosVida).max().orElse(100);
    int danoMaximoPermitido = vidaMaximaCaravana / 2; 
    int longitudAjustada = Math.max(10, longitud);
    double factorLongitud = 140.0 / longitudAjustada;
    factorLongitud = Math.min(1.0, factorLongitud / 5.0); 
    return (int) (danoMaximoPermitido * factorLongitud);
}
}

//Math.max es un método estático de la clase Math en Java. 
//Este método toma dos valores como argumentos y devuelve el mayor de los dos.