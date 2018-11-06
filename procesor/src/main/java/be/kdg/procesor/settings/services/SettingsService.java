package be.kdg.procesor.settings.services;

import be.kdg.procesor.settings.exceptions.ProcessorSettingsException;
import be.kdg.procesor.settings.model.ProcessorSettings;
import be.kdg.procesor.settings.persistence.SettingsRepository;
import be.kdg.procesor.violations.exceptions.ViolationException;
import be.kdg.procesor.violations.model.Violation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SettingsService {
    private final SettingsRepository settingsRepository;

    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public ProcessorSettings save(ProcessorSettings processorSettings) {
        return settingsRepository.save(processorSettings);
    }

    public ProcessorSettings load(Long id) throws ProcessorSettingsException {
        Optional<ProcessorSettings> optionalProcessorSettings = settingsRepository.findById(id);
        if (optionalProcessorSettings.isPresent()) {
            return optionalProcessorSettings.get();
        }
        throw new ProcessorSettingsException("Settings not found");
    }

    public ProcessorSettings updateSettings(ProcessorSettings processorSettings) {
        //ProcessorSettings settings = new ProcessorSettings(emissionFineFactor,speedingFineFactor,timeFrameBufferingSegments,timeFrameDoubleEmission,retryDelay,retryCount);
        processorSettings.setId(1L);
        return save(processorSettings);
    }
}
