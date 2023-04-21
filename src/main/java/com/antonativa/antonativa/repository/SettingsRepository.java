package com.antonativa.antonativa.repository;

import com.antonativa.antonativa.models.Settings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends CrudRepository<Settings, Long> {

}
