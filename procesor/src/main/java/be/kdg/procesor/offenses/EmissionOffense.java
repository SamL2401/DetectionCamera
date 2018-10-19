package be.kdg.procesor.offenses;

import be.kdg.procesor.model.cameras.DetectionCamera;
import be.kdg.procesor.model.cars.LicensePlate;
import be.kdg.procesor.model.messages.CameraMessage;
import be.kdg.procesor.model.violations.Violation;
import be.kdg.procesor.services.NetworkService;
import be.kdg.procesor.services.ViolationService;
import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.CameraServiceProxy;
import be.kdg.sa.services.LicensePlateNotFoundException;
import be.kdg.sa.services.LicensePlateServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@ConditionalOnProperty(name = "offense.type", havingValue = "emission")
public class EmissionOffense implements Offense {
    private final Logger LOGGER = LoggerFactory.getLogger(EmissionOffense.class);

    private final CameraServiceProxy cameraServiceProxy;
    private final LicensePlateServiceProxy licensePlateServiceProxy;
    private final ObjectMapper objectMapper;
    private final NetworkService networkService;
    private final ViolationService violationService;

    public EmissionOffense(CameraServiceProxy cameraServiceProxy, LicensePlateServiceProxy licensePlateServiceProxy, ObjectMapper objectMapper, NetworkService networkService, ViolationService violationService) {
        this.cameraServiceProxy = cameraServiceProxy;
        this.licensePlateServiceProxy = licensePlateServiceProxy;
        this.objectMapper = objectMapper;
        this.networkService = networkService;
        this.violationService = violationService;
    }

    @Override
    public void detect(CameraMessage cameraMessage) {
        try {
            DetectionCamera camera = networkService.getCamera(cameraMessage.getId());
            LicensePlate licensePlate = networkService.getLicensePlate(cameraMessage.getLicensePlate());

            if (licensePlate.getEuroNumber() < camera.getEuroNorm()) {
                Violation violation = new Violation(250.0, LocalDateTime.now(),true,"");
                LOGGER.info("WARNING EURONORM VIOLATION:  " + violation.toString());
                violationService.save(violation);
            }
//            if (camera.getCameraId())
        } catch (CameraNotFoundException | LicensePlateNotFoundException | IOException e) {
            LOGGER.warn(e.getLocalizedMessage());
        }
        System.out.println(cameraMessage);
    }
}
