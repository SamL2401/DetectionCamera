package be.kdg.procesor.detectors.offenses;

import be.kdg.procesor.messages.model.messages.CameraMessage;
import be.kdg.procesor.cameras.model.DetectionCamera;
import be.kdg.procesor.cars.model.LicensePlate;
import be.kdg.procesor.detectors.services.ProxyServiceHandler;
import be.kdg.procesor.violations.model.violations.Violation;
import be.kdg.procesor.violations.services.ViolationService;
import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
//@ConditionalOnProperty(name = "offense.type", havingValue = "emission")
public class EmissionOffense{
    private final Logger LOGGER = LoggerFactory.getLogger(EmissionOffense.class);

    private final ProxyServiceHandler proxyServiceHandler;
    private final ViolationService violationService;

    public EmissionOffense(ProxyServiceHandler proxyServiceHandler, ViolationService violationService) {
        this.proxyServiceHandler = proxyServiceHandler;
        this.violationService = violationService;
    }

    public void detect(CameraMessage cameraMessage) {
        try {
            DetectionCamera camera = proxyServiceHandler.getCamera(cameraMessage.getId());
            LicensePlate licensePlate = proxyServiceHandler.getLicensePlate(cameraMessage.getLicensePlate());

            if (licensePlate.getEuroNumber() < camera.getEuroNorm()) {
                Violation violation = new Violation(250.0, LocalDateTime.now(), true, "");
                LOGGER.info("WARNING EURONORM VIOLATION:  " + violation.toString());
                violationService.save(violation);
            }
        } catch (CameraNotFoundException | LicensePlateNotFoundException | IOException e) {
            LOGGER.warn(e.getLocalizedMessage());
        }
        System.out.println(cameraMessage);
    }
}
