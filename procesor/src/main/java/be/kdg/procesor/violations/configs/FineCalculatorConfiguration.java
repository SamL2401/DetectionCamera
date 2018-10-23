package be.kdg.procesor.violations.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This is a configuration file for the fine calculator settings
 *
 * @author Sam Laureys
 */
@Component
@ConfigurationProperties
public class FineCalculatorConfiguration {
    private double emissionFineFactor;
    private double speedingFineFactor;
    private double timeFragment;

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

    public double getTimeFragment() {
        return timeFragment;
    }

    public void setTimeFragment(double timeFragment) {
        this.timeFragment = timeFragment;
    }
}
