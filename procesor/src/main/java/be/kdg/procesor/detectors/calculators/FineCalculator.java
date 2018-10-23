package be.kdg.procesor.detectors.calculators;

import be.kdg.procesor.violations.configs.FineCalculatorConfiguration;
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

    private final FineCalculatorConfiguration fineCalculatorConfiguration;

    public FineCalculator(FineCalculatorConfiguration fineCalculatorConfiguration) {
        this.fineCalculatorConfiguration = fineCalculatorConfiguration;
    }

    public double calculate(String type) {
        double fine = 230;
        if (type.equals("emission")) {
            fine = fineCalculatorConfiguration.getEmissionFineFactor();
            LOGGER.info("emission calculated");
        }
        return fine;
    }
}
