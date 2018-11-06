package be.kdg.procesor.detectors.offenses;

import be.kdg.procesor.messages.model.messages.CameraMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpeedingOffenseTest {

    @Autowired
    private SpeedingOffense speedingOffense;

    @Test
    public void detect() {
        CameraMessage cameraMessage1 = new CameraMessage(1, "1-ABC-123", LocalDateTime.now());
        CameraMessage cameraMessage2 = new CameraMessage(2, "1-ABC-123", LocalDateTime.now().plusSeconds(2));

        speedingOffense.detect(cameraMessage1);
        speedingOffense.detect(cameraMessage2);
    }
}