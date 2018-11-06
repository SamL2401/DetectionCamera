package be.kdg.procesor.settings.configs;

import be.kdg.procesor.settings.model.ProcessorSettings;
import be.kdg.procesor.settings.services.SettingsService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * This is a configuration file for the fine calculator settings
 *
 * @author Sam Laureys
 */
@Component
@ConfigurationProperties
public class SettingsProcessorConfiguration {
    private double emissionFineFactor;
    private double speedingFineFactor;
    private long timeFrameBufferingSegments;
    private long timeFrameDoubleEmission;
    private long retryDelay;
    private int retryCount;

    private final SettingsService settingsService;

    public SettingsProcessorConfiguration(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @PostConstruct
    private void setProcessorSettingsDB() {
        ProcessorSettings processorSettings = new ProcessorSettings(emissionFineFactor, speedingFineFactor, timeFrameBufferingSegments, timeFrameDoubleEmission, retryDelay, retryCount);
        settingsService.save(processorSettings);
    }

    public double getEmissionFineFactor() {
        return emissionFineFactor;
    }

    public void setEmissionFineFactor(double emissionFineFactor) {
        this.emissionFineFactor = emissionFineFactor;
    }

    public double getSpeedingFineFactor() {
        return speedingFineFactor;
    }

    public void setSpeedingFineFactor(double speedingFineFactor) {
        this.speedingFineFactor = speedingFineFactor;
    }

    public long getTimeFrameBufferingSegments() {
        return timeFrameBufferingSegments;
    }

    public void setTimeFrameBufferingSegments(long timeFrameBufferingSegments) {
        this.timeFrameBufferingSegments = timeFrameBufferingSegments;
    }

    public long getTimeFrameDoubleEmission() {
        return timeFrameDoubleEmission;
    }

    public void setTimeFrameDoubleEmission(long timeFrameDoubleEmission) {
        this.timeFrameDoubleEmission = timeFrameDoubleEmission;
    }

    public long getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(long retryDelay) {
        this.retryDelay = retryDelay;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}
