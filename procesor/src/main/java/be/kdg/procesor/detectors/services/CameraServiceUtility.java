package be.kdg.procesor.detectors.services;

import be.kdg.sa.services.CameraServiceProxy;
import org.springframework.stereotype.Component;

@Component
public class CameraServiceUtility {
    private final CameraServiceProxy cameraServiceProxy;

    public CameraServiceUtility(CameraServiceProxy cameraServiceProxy) {
        this.cameraServiceProxy = cameraServiceProxy;
    }

    public CameraServiceProxy getCameraServiceProxy() {
        return cameraServiceProxy;
    }
}
