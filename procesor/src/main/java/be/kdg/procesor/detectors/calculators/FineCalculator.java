package be.kdg.procesor.detectors.calculators;

import be.kdg.procesor.settings.configs.SettingsProcessorConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This class calculates the fine for an offense
 *
 * @author Sam Laureys
 */
@Component
public class FineCalculator {
    private final Logger LOGGER = LoggerFactory.getLogger(FineCalculator.class);

    private final SettingsProcessorConfiguration settingsProcessorConfiguration;

    public FineCalculator(SettingsProcessorConfiguration settingsProcessorConfiguration) {
        this.settingsProcessorConfiguration = settingsProcessorConfiguration;
    }

    public double calculate() {
        LOGGER.info("emission calculated");
        return settingsProcessorConfiguration.getEmissionFineFactor();
    }

    public double calculate(float speed, float speedlimit) {
        LOGGER.info("emission calculated");
        return (speed - speedlimit) * settingsProcessorConfiguration.getSpeedingFineFactor();
    }
}
