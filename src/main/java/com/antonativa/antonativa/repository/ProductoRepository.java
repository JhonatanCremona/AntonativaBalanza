package com.antonativa.antonativa.repository;

import com.antonativa.antonativa.models.Etiqueta;
import com.antonativa.antonativa.models.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
}
