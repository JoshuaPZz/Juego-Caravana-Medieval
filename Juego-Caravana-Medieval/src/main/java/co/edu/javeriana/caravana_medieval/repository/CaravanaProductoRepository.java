package co.edu.javeriana.caravana_medieval.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval.model.CaravanaProducto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CaravanaProductoRepository extends JpaRepository<CaravanaProducto, Long> {

    @Query("SELECT cp FROM CaravanaProducto cp WHERE cp.caravana.id = :idCaravana")
    List<CaravanaProducto> findByIdCaravana(@Param("idCaravana") Long idCaravana);

}
