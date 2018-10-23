package be.kdg.procesor.detectors.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testing for ProxyServices
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyServiceHandlerTest {

    @Autowired
    private ProxyServiceHandler proxyServiceHandler;

    @Test
    public void testCameraService() throws Exception {
        assertNotEquals(proxyServiceHandler.getCamera(1), proxyServiceHandler.getCamera(2));
        assertNotNull(proxyServiceHandler.getCamera(1));
    }

    @Test
    public void testLicensePlateService() throws Exception {
        assertNotNull(proxyServiceHandler.getLicensePlate("4-ABC-123"));
    }
}