package be.kdg.procesor.settings.services;

import be.kdg.procesor.settings.configs.SettingsProcessorConfiguration;
import be.kdg.procesor.settings.exceptions.ProcessorSettingsException;
import be.kdg.procesor.settings.model.ProcessorSettings;
import be.kdg.procesor.settings.persistence.SettingsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SettingsService {
    private final SettingsRepository settingsRepository;
    private final SettingsProcessorConfiguration settingsProcessorConfiguration;

    public SettingsService(SettingsRepository settingsRepository, SettingsProcessorConfiguration settingsProcessorConfiguration) {
        this.settingsRepository = settingsRepository;
        this.settingsProcessorConfiguration = settingsProcessorConfiguration;
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
        saveToLocal(processorSettings);
        return save(processorSettings);
    }

    private void saveToLocal(ProcessorSettings processorSettings) {
        settingsProcessorConfiguration.setEmissionFineFactor(processorSettings.getEmissionFineFactor());
        settingsProcessorConfiguration.setSpeedingFineFactor(processorSettings.getSpeedingFineFactor());
        settingsProcessorConfiguration.setRetryCount(processorSettings.getRetryCount());
        settingsProcessorConfiguration.setRetryDelay(processorSettings.getRetryDelay());
        settingsProcessorConfiguration.setTimeFrameBufferingSegments(processorSettings.getTimeFrameBufferingSegments());
        settingsProcessorConfiguration.setTimeFrameDoubleEmission(processorSettings.getTimeFrameDoubleEmission());
    }
}
