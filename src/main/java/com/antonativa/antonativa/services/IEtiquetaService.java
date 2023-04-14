package com.antonativa.antonativa.services;

import com.antonativa.antonativa.models.EtiquetaDTO;

import java.util.Collection;

public interface IEtiquetaService {
    void createEtiqueta(EtiquetaDTO eDto);
    Collection<EtiquetaDTO> getAllEtiqueta();
}
