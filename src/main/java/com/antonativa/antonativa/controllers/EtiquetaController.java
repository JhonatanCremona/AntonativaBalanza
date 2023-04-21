package com.antonativa.antonativa.controllers;


import com.antonativa.antonativa.models.Etiqueta;
import com.antonativa.antonativa.models.EtiquetaDTO;
import com.antonativa.antonativa.repository.EtiquetaRepository;
import com.antonativa.antonativa.services.EtiquetaService;
import com.antonativa.antonativa.settings.impresion.ImpresionInmediata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Scanner;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class EtiquetaController {

    @Autowired
    public EtiquetaService etiquetaService;
    @Autowired
    EtiquetaRepository etiquetaRepository;

    @PostMapping("/guardar")
    /*public Etiqueta saveEtiqueta(@RequestBody Etiqueta etiqueta) {
        return etiquetaService.save(etiqueta);
    }*/
    public ResponseEntity<?> guardarEtiqueta(@RequestBody EtiquetaDTO etiquetaDto) {
        etiquetaService.createEtiqueta(etiquetaDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/listar")
    /*public Iterable<Etiqueta> findAll() {
        return etiquetaService.findAll();
    }*/
    public Collection<EtiquetaDTO> listEtiquetas() {return etiquetaService.getAllEtiqueta();}

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        etiquetaService.eliminar(id);

        return "Se ha eliminado exitosamente la etiqueta";

    }

    @GetMapping("/imprimir/{id}")
    public String imprimir(@PathVariable long id) {

        etiquetaService.setEstadoById(id);

        for (Etiqueta etiqueta : etiquetaRepository.findAll()) {

            if (etiqueta.isEstado()) {

                etiqueta.setPesoNeto(etiquetaService.getPesoNeto());

                if(etiqueta.getPesoNeto() != null) {

                    ImpresionInmediata.imprimirEtiqueta(etiqueta);

                }

            }

        }

        return "Impresion realizada";

    }

}
