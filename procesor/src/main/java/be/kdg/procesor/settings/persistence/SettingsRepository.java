package be.kdg.procesor.settings.persistence;

import be.kdg.procesor.settings.model.ProcessorSettings;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * the repo for the settings
 */
public interface SettingsRepository extends JpaRepository<ProcessorSettings,Long> {
}
