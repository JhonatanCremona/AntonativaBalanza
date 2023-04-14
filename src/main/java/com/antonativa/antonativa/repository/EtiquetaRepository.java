package com.antonativa.antonativa.repository;

import com.antonativa.antonativa.models.Etiqueta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtiquetaRepository extends CrudRepository<Etiqueta, Long> {



}
