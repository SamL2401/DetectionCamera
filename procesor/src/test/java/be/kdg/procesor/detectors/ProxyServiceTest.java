package be.kdg.procesor.detectors;

import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.CameraServiceProxy;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateServiceProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Testing the ProxyServices exceptions
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyServiceTest {

    @Autowired
    private CameraServiceProxy cameraServiceProxy;
    @Autowired
    private LicensePlateServiceProxy licensePlateServiceProxy;

    private int falseCamera;
    private String falseLicensePlate;

    @Before
    public void setUp() {
        falseCamera = 200;
        falseLicensePlate = "5-ADS";
    }

    @Test(expected = InvalidLicensePlateException.class)
    public void testLicensePlateService() {
        try {
            licensePlateServiceProxy.get(falseLicensePlate);

        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Test(expected = CameraNotFoundException.class)
    public void testCameraService() {
        try {
            cameraServiceProxy.get(falseCamera);

        } catch (IOException e) {
            e.getMessage();
        }
    }
}