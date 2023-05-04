package com.antonativa.antonativa.services;

import java.io.IOException;
import java.net.InetAddress;

import com.antonativa.antonativa.models.Settings;
import com.antonativa.antonativa.repository.SettingsRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {
    @Autowired
    SettingsRepository settingsRepository;
    @Autowired
    Settings settings;
    private boolean resultadoConexion = false;
    private static final Logger logger = Logger.getLogger(SettingsService.class);
    private boolean conexionModulo(String IpModulo) {
        try {
            InetAddress direccion = InetAddress.getByName(IpModulo);
            return resultadoConexion = direccion.isReachable(10000);
        } catch (IOException e) {
            logger.fatal("IP sin conexion, error: " + e.getMessage());
            return resultadoConexion;
        }
    }
    private boolean conexionImpresora(String impresora) {
        return false;
    }

    public void guardarSettings() {
        settings.setEstadoModuloBalanza(conexionModulo("10.1.0.1"));
        settings.setEstadoImpresora(conexionImpresora(""));

        settingsRepository.save(settings);
    }

}
