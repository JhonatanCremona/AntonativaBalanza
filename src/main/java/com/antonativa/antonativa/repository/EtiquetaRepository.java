package com.antonativa.antonativa.repository;

import com.antonativa.antonativa.models.Etiqueta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EtiquetaRepository extends CrudRepository<Etiqueta, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE etiqueta SET estado = :estado WHERE id = :id", nativeQuery = true)
    void setEstadoById(@Param("id") Long id, @Param("estado") boolean estado);

}
