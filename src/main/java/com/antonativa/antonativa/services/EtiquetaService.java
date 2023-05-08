package com.antonativa.antonativa.services;

import com.antonativa.antonativa.models.Etiqueta;
import com.antonativa.antonativa.models.EtiquetaDTO;
import com.antonativa.antonativa.repository.EtiquetaRepository;
import com.antonativa.antonativa.settings.procesamiento.ProcesamientoDatos;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
@Service
public class EtiquetaService implements IEtiquetaService {

    @Autowired
    EtiquetaRepository etiquetaRepository;

    @Autowired
    ObjectMapper mapper;

    private void saveEtiqueta(EtiquetaDTO etiquetaDTO) {
        Etiqueta newEtiqueta = mapper.convertValue(etiquetaDTO, Etiqueta.class);
        etiquetaRepository.save(newEtiqueta);
        etiquetaRepository.findAll();
    }
    @Override
    public void createEtiqueta(EtiquetaDTO eDto) {
        saveEtiqueta(eDto);
    }

    @Override
    public Collection<EtiquetaDTO> getAllEtiqueta() {
        List<Etiqueta> allEtiquetas = (List<Etiqueta>) etiquetaRepository.findAll();
        Set<EtiquetaDTO> allEtiquetasDto = new HashSet<>();
        for (Etiqueta etiqueta : allEtiquetas) {
            allEtiquetasDto.add(mapper.convertValue(etiqueta, EtiquetaDTO.class));
        }
        return allEtiquetasDto;
    }

    public Etiqueta findById(Long id) {
        return etiquetaRepository.findById(id).get();
    }

    public void eliminar(Long id) {
        etiquetaRepository.deleteById(id);
    }

    public boolean obtenerEtiqueta(Long id) {
        return etiquetaRepository.findById(id).isPresent();
    }

    public String getPesoNeto() {
        final String HOST = "192.168.0.71";
        final int PORT = 8082;
        DataInputStream in;
        byte[] mensaje;

        try {

            Socket sc = new Socket(HOST, PORT);
            in = new DataInputStream(sc.getInputStream());
            mensaje = in.readNBytes(8);
            sc.close();

            return ProcesamientoDatos.procesarDatosBalanza(mensaje) + " KG";

        } catch (IOException ex) {
            Logger.getLogger(EtiquetaService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void setEstadoById(long id) {

        for (Etiqueta etiqueta : etiquetaRepository.findAll()) {

                etiquetaRepository.setEstadoById(etiqueta.getId(),false);

        }

        etiquetaRepository.findById(id).get().setEstado(true);

    }

}
