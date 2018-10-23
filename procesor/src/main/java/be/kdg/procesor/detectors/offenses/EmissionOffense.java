package be.kdg.procesor.detectors.offenses;

import be.kdg.procesor.detectors.calculators.FineCalculator;
import be.kdg.procesor.detectors.model.cameras.DetectionCamera;
import be.kdg.procesor.detectors.model.cars.LicensePlate;
import be.kdg.procesor.detectors.services.ProxyServiceHandler;
import be.kdg.procesor.messages.model.messages.CameraMessage;
import be.kdg.procesor.violations.model.Violation;
import be.kdg.procesor.violations.services.ViolationService;
import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * This class is responsible for detecting an emission offense
 *
 * @author Sam Laureys
 * @version 1.03
 */
@Component
public class EmissionOffense {
    private final Logger LOGGER = LoggerFactory.getLogger(EmissionOffense.class);

    private final ProxyServiceHandler proxyServiceHandler;
    private final ViolationService violationService;
    private final FineCalculator fineCalculator;

    public EmissionOffense(ProxyServiceHandler proxyServiceHandler, ViolationService violationService, FineCalculator fineCalculator) {
        this.proxyServiceHandler = proxyServiceHandler;
        this.violationService = violationService;
        this.fineCalculator = fineCalculator;
    }

    public void detect(CameraMessage cameraMessage) {
        try {
            DetectionCamera camera = proxyServiceHandler.getCamera(cameraMessage.getId());
            LicensePlate licensePlate = proxyServiceHandler.getLicensePlate(cameraMessage.getLicensePlate());

            if (licensePlate.getEuroNumber() < camera.getEuroNorm()) {
                String type = "emission";
                Violation violation = new Violation(fineCalculator.calculate(type), type, cameraMessage.getLicensePlate(), cameraMessage.getTimestamp());
                LOGGER.info("WARNING EURONORM VIOLATION:  " + violation.toString());
                violationService.save(violation);
            }
        } catch (CameraNotFoundException | LicensePlateNotFoundException | IOException e) {
            LOGGER.warn(e.getLocalizedMessage());
        }
        System.out.println(cameraMessage);
    }
}
