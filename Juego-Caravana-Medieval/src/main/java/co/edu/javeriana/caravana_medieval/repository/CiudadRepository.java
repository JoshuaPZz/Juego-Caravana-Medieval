package co.edu.javeriana.caravana_medieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval.model.Ciudad;
import jakarta.transaction.Transactional;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM CIUDAD_PRODUCTO WHERE ID_CIUDAD = :idCiudad")
    void deleteCiudadProducto(@Param("idCiudad") Long idCiudad);
    
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM CIUDAD_SERVICIO WHERE ID_CIUDAD = :idCiudad")
    void deleteCiudadServicio(@Param("idCiudad") Long idCiudad);
    
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM RUTA WHERE CIUDAD_ORIGEN = :idCiudad OR CIUDAD_DESTINO = :idCiudad")
    void deleteRutas(@Param("idCiudad") Long idCiudad);
    
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM SERVICIO_COMPRA WHERE ID_CIUDAD = :idCiudad")
    void deleteServicioCompra(@Param("idCiudad") Long idCiudad);
    
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM CIUDAD WHERE ID = :idCiudad")
    void deleteCiudad(@Param("idCiudad") Long idCiudad);
    
    // Método para ejecutar todas las operaciones de borrado en el orden correcto
    @Transactional
    default void deleteCiudadCascada(Long idCiudad) {
        deleteCiudadProducto(idCiudad);
        deleteCiudadServicio(idCiudad);
        deleteRutas(idCiudad);
        deleteServicioCompra(idCiudad);
        deleteCiudad(idCiudad);
    }
}
