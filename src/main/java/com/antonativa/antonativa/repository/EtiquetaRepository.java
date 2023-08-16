package com.antonativa.antonativa.repository;

import com.antonativa.antonativa.models.Etiqueta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EtiquetaRepository extends CrudRepository<Etiqueta, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE etiqueta SET estado = :estado WHERE id = :id", nativeQuery = true)
    void setEstadoById(@Param("id") Long id, @Param("estado") boolean estado);

    @Transactional
    @Modifying
    @Query(value = "UPDATE etiqueta SET producto = :producto WHERE id = :id", nativeQuery = true)
    void setProductoById(@Param("id") Long id, @Param("producto") String producto);

    @Transactional
    @Modifying
    @Query(value = "UPDATE etiqueta SET lote = :lote WHERE id = :id", nativeQuery = true)
    void setLoteById(@Param("id") Long id, @Param("lote") String lote);

    @Transactional
    @Modifying
    @Query(value = "UPDATE etiqueta SET fecha_vencimiento = :fecha_vencimiento WHERE id = :id", nativeQuery = true)
    void setFechaVencimientoById(@Param("id") Long id, @Param("fecha_vencimiento") LocalDate fechaVencimiento);

    @Transactional
    @Modifying
    @Query(value = "UPDATE etiqueta SET unidades = :unidades WHERE id = :id", nativeQuery = true)
    void setUnidadesById(@Param("id") Long id, @Param("unidades") String unidades);

}
