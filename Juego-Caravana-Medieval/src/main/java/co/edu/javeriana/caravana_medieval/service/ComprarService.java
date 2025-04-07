package co.edu.javeriana.caravana_medieval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.model.Ciudad;
import co.edu.javeriana.caravana_medieval.repository.CaravanaRepository;

@Service
public class ComprarService {

    @Autowired
    private CaravanaRepository caravanaRepository;
    @Autowired
    private CaravanaService caravanaService;
    

    public void comprarServicio(Long idCarvana, Long idServicio) {

    Caravana caravana = caravanaService.getCaravanaById(idCarvana);
    Ciudad ciudad = caravanaService.getCiudadActual(idCarvana);

        // Lógica para comprar un servicio en la ciudad
        // 1. Verificar si la caravana tiene suficiente dinero
        // 2. Verificar si el servicio está disponible en la ciudad
        // 3. Realizar la compra y actualizar el estado de la caravana y el servicio

        caravana = caravanaRepository.saveAndFlush(caravana);
    }

    public void comprarProducto(Long idCaravana, Long idProducto) {
        Caravana caravana = caravanaService.getCaravanaById(idCaravana);
        Ciudad ciudad = caravanaService.getCiudadActual(idCaravana);
        // Lógica para comprar un producto en la ciudad
        // 1. Verificar si la caravana tiene suficiente dinero
        // 2. Verificar si el producto está disponible en la ciudad
        // 3. Realizar la compra y actualizar el estado de la caravana y el producto
        caravana = caravanaRepository.saveAndFlush(caravana);
    }
    
}
