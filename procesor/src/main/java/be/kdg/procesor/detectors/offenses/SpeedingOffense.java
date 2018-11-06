package be.kdg.procesor.detectors.offenses;

import be.kdg.procesor.detectors.calculators.FineCalculator;
import be.kdg.procesor.detectors.model.cameras.DetectionCamera;
import be.kdg.procesor.detectors.model.cameras.Segment;
import be.kdg.procesor.detectors.offenses.buffers.CameraMessageBuffer;
import be.kdg.procesor.detectors.offenses.buffers.SpeedingCameraMessagesBuffer;
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
import java.time.Duration;
import java.util.List;
import java.util.Optional;

/**
 * This class is responsible for detecting an speeding offense
 *
 * @author Sam Laureys
 * @version 1.0
 */
@Component
public class SpeedingOffense {
    private final Logger LOGGER = LoggerFactory.getLogger(SpeedingOffense.class);

    private final ProxyServiceHandler proxyServiceHandler;
    private final ViolationService violationService;
    private final FineCalculator fineCalculator;
    private final SpeedingCameraMessagesBuffer speedingCameraMessagesBuffer;
    private final CameraMessageBuffer cameraMessageBuffer;

    public SpeedingOffense(ProxyServiceHandler proxyServiceHandler, ViolationService violationService, FineCalculator fineCalculator, SpeedingCameraMessagesBuffer speedingCameraMessagesBuffer, CameraMessageBuffer cameraMessageBuffer) {
        this.proxyServiceHandler = proxyServiceHandler;
        this.violationService = violationService;
        this.fineCalculator = fineCalculator;
        this.speedingCameraMessagesBuffer = speedingCameraMessagesBuffer;
        this.cameraMessageBuffer = cameraMessageBuffer;
    }

    public void detect(CameraMessage cameraMessage) {
        try {
            Optional<List<CameraMessage>> optionalCameraMessage = speedingCameraMessagesBuffer.getCameraMessage(cameraMessage.getLicensePlate());
            if (optionalCameraMessage.isPresent()) {
                List<CameraMessage> cameraMessagesSaved = optionalCameraMessage.get();
                for (CameraMessage messageSaved : cameraMessagesSaved) {
                    Segment segment = proxyServiceHandler.getCamera(messageSaved.getId()).getSegment();
                    if (segment.getConnectedCameraId() == cameraMessage.getId()) {
                        float secondsBetweenCameras = Duration.between(messageSaved.getTimestamp(), cameraMessage.getTimestamp()).toSeconds();
                        float speedMeterPerSecond = segment.getDistance() / secondsBetweenCameras;
                        float speedKmPerHour = speedMeterPerSecond / 1000 * 60 * 60;
                        float speedLimit = segment.getSpeedLimit();
                        if (speedKmPerHour > speedLimit) {
                            LOGGER.info(String.format("SPEEDING VIOLATION:  %.2fkm/h in a %fkm/h zone", speedKmPerHour, speedLimit ));
                            Violation violation = new Violation(fineCalculator.calculate(speedKmPerHour,speedLimit), "speeding", cameraMessage.getLicensePlate(), cameraMessage.getTimestamp());
                            violationService.save(violation);
                        }
                    }
                }
            }
            DetectionCamera camera = proxyServiceHandler.getCamera(cameraMessage.getId());
            if (camera.getSegment() != null) {
                speedingCameraMessagesBuffer.addCameraMessage(cameraMessage);
            }
        } catch (CameraNotFoundException | LicensePlateNotFoundException | IOException e) {
            LOGGER.warn(e.getLocalizedMessage()+" and send to buffer");
            //cameraMessageBuffer.addCameraMessage(cameraMessage);
        }
    }

    private void lookForConectedCamera() {

    }
}
