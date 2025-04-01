package co.edu.javeriana.caravana_medieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.javeriana.caravana_medieval.model.Caravana;

@Repository
public interface CaravanaRepository extends JpaRepository<Caravana, Long> {

   @Query("SELECT c.ciudadActual.id FROM Caravana c WHERE c.id = :caravanaId")
Long findCiudadActualIdByCaravanaId(@Param("caravanaId") Long caravanaId);

}
