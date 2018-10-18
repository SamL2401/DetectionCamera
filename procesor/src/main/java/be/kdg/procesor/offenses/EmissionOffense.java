package be.kdg.procesor.offenses;

import be.kdg.procesor.model.cameras.DetectionCamera;
import be.kdg.procesor.model.messages.CameraMessage;
import be.kdg.sa.services.CameraServiceProxy;
import be.kdg.sa.services.LicensePlateServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "offense.type", havingValue = "emission")
public class EmissionOffense implements Offense {
    private final Logger LOGGER = LoggerFactory.getLogger(EmissionOffense.class);

    private final CameraServiceProxy cameraServiceProxy;
    private final LicensePlateServiceProxy licensePlateServiceProxy;
    private final ObjectMapper objectMapper;

    public EmissionOffense(CameraServiceProxy cameraServiceProxy, LicensePlateServiceProxy licensePlateServiceProxy, ObjectMapper objectMapper) {
        this.cameraServiceProxy = cameraServiceProxy;
        this.licensePlateServiceProxy = licensePlateServiceProxy;
        this.objectMapper = objectMapper;
    }

    @Override
    public void detect(CameraMessage cameraMessage) {
        try {
            String camJson = cameraServiceProxy.get(cameraMessage.getId());
            System.out.println(camJson);
            //DetectionCamera camera = objectMapper.readValue(camJson, DetectionCamera.class);
            //LOGGER.info("Read camera from service: " + camera);
//
//            String licensePlateJson = licensePlateServiceProxy.get(cameraMessage.getLicensePlate());
//
//            LicensePlate licensePlate = objectMapper.readValue(licensePlateJson, LicensePlate.class);
//            LOGGER.info("Read license plate from service: " + licensePlate);
//
//            if (licensePlate.getEuroNumber() < camera.getEuroNorm()){
//                String violation =  String.format("License Plate %s euronorm: %d is lower than allowed euronorm %d ",
//                        licensePlate.getPlateId(), licensePlate.getEuroNumber(), camera.getEuroNorm());
//                LOGGER.info("WARNING EURONORM VIOLATION:  " + violation);
//            }
        } catch (Exception e) {
            LOGGER.error("Failed to detect Emission");
        }
        System.out.println(cameraMessage);
    }
}
