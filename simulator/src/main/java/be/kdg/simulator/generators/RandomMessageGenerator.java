package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@ConditionalOnProperty(name="generator.type",havingValue = "random")
public class RandomMessageGenerator implements MessageGenerator {
    @Value("${aantalCameras.val}")
    private int cameraMax;
    private String licensePlate = "";

    Random rand = new Random();

    private String randomLicense(){
        StringBuilder s = new StringBuilder("1-");
        /*
        s.append(rand.nextInt(9)+1);
        s.append("-");
        */
        for (int i = 0; i < 3; i++) {
            char ch = (char) (Math.random() * 26 + 'A');
            s.append(ch);
        }
        s.append("-");
        for (int i = 0; i < 3; i++) {
            char digit1 = (char) (Math.random() * 10 + '0');
            s.append(digit1);
        }
        return s.toString();
    }
    @Override
    public CameraMessage generate() {
        return new CameraMessage(rand.nextInt(cameraMax)+1,randomLicense(), LocalDateTime.now());
    }


}