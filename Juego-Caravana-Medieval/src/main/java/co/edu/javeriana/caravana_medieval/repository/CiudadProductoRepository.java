package co.edu.javeriana.caravana_medieval.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval.model.CiudadProducto;

@Repository
public interface CiudadProductoRepository extends JpaRepository<CiudadProducto, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM CiudadProducto cp WHERE cp.ciudad.id = :ciudadId")
    void deleteByCiudadId(@Param("ciudadId") Long ciudadId);

   


}
