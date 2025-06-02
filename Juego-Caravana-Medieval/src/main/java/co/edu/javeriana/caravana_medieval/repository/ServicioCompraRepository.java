package co.edu.javeriana.caravana_medieval.repository;

import co.edu.javeriana.caravana_medieval.model.ServicioCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServicioCompraRepository extends JpaRepository<ServicioCompra, Long> {
    
    // Método para verificar si ya existe una compra del mismo servicio en la misma ciudad por la misma caravana
    Optional<ServicioCompra> findByCaravanaIdAndCiudadIdAndServicioId(Long caravanaId, Long ciudadId, Long servicioId);
    
    List<ServicioCompra> findByCaravanaIdAndCiudadId(Long caravanaId, Long ciudadId);
}