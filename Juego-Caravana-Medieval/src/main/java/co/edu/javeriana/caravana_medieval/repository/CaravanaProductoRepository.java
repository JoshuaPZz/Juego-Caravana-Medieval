package co.edu.javeriana.caravana_medieval.repository;


import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval.model.CaravanaProducto;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CaravanaProductoRepository extends JpaRepository<CaravanaProducto, Long> {
    
}
