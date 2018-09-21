package be.kdg.procesor;

import be.kdg.procesor.generators.MessageGenerator;
import be.kdg.procesor.model.CameraMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcesorApplicationTests {

    @Autowired
    // Beste zelf, Dit is Field Injection
    private MessageGenerator messageGenerator;

    @Test
    public void testMessageGenerator() {
        CameraMessage cameraMessage = messageGenerator.generate();
        Assert.assertTrue(cameraMessage.getLicensePlate().equalsIgnoreCase("1-EYE-957"));

    }

}
