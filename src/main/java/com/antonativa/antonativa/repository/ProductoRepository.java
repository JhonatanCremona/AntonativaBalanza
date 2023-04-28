package com.antonativa.antonativa.repository;


import com.antonativa.antonativa.models.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

    @Query(value = "SELECT * FROM antonativa.producto WHERE fecha_actual >= ?1 AND fecha_actual <= ?2;", nativeQuery = true)
    Collection<Producto> listProductosByFecha(LocalDateTime fecha1, LocalDateTime fecha2);

}
