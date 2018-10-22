package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomMessageGeneratorTest {

    @Autowired
    private MessageGenerator messageGenerator;

    @Test
    public void testMessageGenerator() {
        Optional<CameraMessage> opt = messageGenerator.generate();
        if (opt.isPresent()) {
            CameraMessage cameraMessage = opt.get();
            Assert.assertTrue(cameraMessage.getLicensePlate().matches("^[1-9]-[A-Z]{3}-[0-9]{3}$"));
        }
    }

    @Test
    public void testRandomMessageLength() {
        Optional<CameraMessage> opt = messageGenerator.generate();
        if (opt.isPresent()) {
            CameraMessage cameraMessage = opt.get();
            assertThat(cameraMessage.getLicensePlate().length(), Matchers.equalTo(9));
        }
    }
}
