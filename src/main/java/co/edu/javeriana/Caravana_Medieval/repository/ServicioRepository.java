package co.edu.javeriana.caravana_medieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    
}
