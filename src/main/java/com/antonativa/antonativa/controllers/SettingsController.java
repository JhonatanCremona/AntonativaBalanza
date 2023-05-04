package com.antonativa.antonativa.controllers;

import com.antonativa.antonativa.models.Settings;
import com.antonativa.antonativa.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/settings")
@CrossOrigin
public class SettingsController {

    Settings settings = new Settings();
    @Autowired
    public SettingsRepository settingsRepository;

    public void setSettings(Settings settings) {

        this.settings.setEstadoImpresora(settings.isEstadoImpresora());

    }

    @GetMapping("/listarimpresoras")
    public Collection<Settings> listSettings() {return (Collection<Settings>) settingsRepository.findAll();}

}
