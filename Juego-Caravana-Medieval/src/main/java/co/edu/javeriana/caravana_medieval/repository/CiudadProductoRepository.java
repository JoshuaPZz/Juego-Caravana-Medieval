package co.edu.javeriana.caravana_medieval.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval.model.CiudadProducto;

import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadProductoRepository extends JpaRepository<CiudadProducto, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM CiudadProducto cp WHERE cp.ciudad.id = :ciudadId")
    void deleteByCiudadId(@Param("ciudadId") Long ciudadId);

    @Query("SELECT cp FROM CiudadProducto cp WHERE cp.ciudad.id = :ciudadId")
    List<CiudadProducto> findByCiudadId(@Param("ciudadId") Long ciudadId);

    @Query("SELECT cp FROM CiudadProducto cp WHERE cp.ciudad.id = :ciudadId AND cp.producto.id = :productoId")
    Optional<CiudadProducto> findByCiudadIdAndProductoId(@Param("ciudadId") Long ciudadId, @Param("productoId") Long productoId);

}