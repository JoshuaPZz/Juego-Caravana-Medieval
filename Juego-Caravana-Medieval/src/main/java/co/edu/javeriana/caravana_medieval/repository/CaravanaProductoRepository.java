package co.edu.javeriana.caravana_medieval.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval.model.CaravanaProducto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CaravanaProductoRepository extends JpaRepository<CaravanaProducto, Long> {

    @Query("SELECT cp FROM CaravanaProducto cp WHERE cp.caravana.id = :idCaravana")
    List<CaravanaProducto> findByIdCaravana(@Param("idCaravana") Long idCaravana);

    @Query("SELECT cp FROM CaravanaProducto cp WHERE cp.producto.id = :idProducto")
    Optional<CaravanaProducto> findByIdProducto(@Param("idProducto") Long idProducto);

    @Query("SELECT cp FROM CaravanaProducto cp WHERE cp.caravana.id = :caravanaId AND cp.producto.id = :productoId")
    Optional<CaravanaProducto> findByCaravanaIdAndProductoId(
            @Param("caravanaId") Long caravanaId, 
            @Param("productoId") Long productoId);
    
    void deleteByCaravanaId(Long caravanaId);

}
