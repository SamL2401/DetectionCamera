package be.kdg.procesor.services;


import be.kdg.procesor.model.cameras.DetectionCamera;
import be.kdg.procesor.model.cars.LicensePlate;
import be.kdg.sa.services.CameraServiceProxy;
import be.kdg.sa.services.LicensePlateServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NetworkService {

    private final Logger LOGGER = LoggerFactory.getLogger(NetworkService.class);

    private final CameraServiceProxy cameraServiceProxy;
    private final LicensePlateServiceProxy licensePlateServiceProxy;
    private final ObjectMapper objectMapper;

    public NetworkService(CameraServiceProxy cameraServiceProxy, LicensePlateServiceProxy licensePlateServiceProxy, ObjectMapper objectMapper) {
        this.cameraServiceProxy = cameraServiceProxy;
        this.licensePlateServiceProxy = licensePlateServiceProxy;
        this.objectMapper = objectMapper;
    }


    public DetectionCamera getCamera(int cameraId) throws IOException {
        String camJson = cameraServiceProxy.get(cameraId);
        DetectionCamera camera = objectMapper.readValue(camJson, DetectionCamera.class);
        LOGGER.info("Read camera from service: " + camera);
        return camera;
    }

    public LicensePlate getLicensePlate(String licensePlateId) throws IOException {
        String licensePlateJson = licensePlateServiceProxy.get(licensePlateId);
        LicensePlate licensePlate = objectMapper.readValue(licensePlateJson, LicensePlate.class);
        LOGGER.info("Read license plate from service: " + licensePlate);
        return licensePlate;

    }


}

