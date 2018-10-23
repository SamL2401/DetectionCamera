package be.kdg.procesor.detectors.configs;

import be.kdg.sa.services.CameraServiceProxy;
import be.kdg.sa.services.LicensePlateServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is a configuration file for the service proxy handling
 * @author Sam Laureys
 */
@Configuration
public class ServicesConfig {

    @Bean
    public CameraServiceProxy cameraServiceProxy() {
        return new CameraServiceProxy();
    }

    @Bean
    public LicensePlateServiceProxy licensePlateServiceProxy() {
        return new LicensePlateServiceProxy();
    }
}
