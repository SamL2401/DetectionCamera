package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

/**
 * This class is responsible for creating random CameraMessages
 * @author Sam Laureys
 * @version 1.02
 */
@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "random")
public class RandomMessageGenerator implements MessageGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);

    @Value("${aantalCameras.val}")
    private int cameraMax;
    @Value("${frequentie.val}")
    private int sleep;
    private Random rand = new Random();

    private String randomLicense() {
        StringBuilder s = new StringBuilder();
        s.append(rand.nextInt(9)+1);
        s.append("-");
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
    public Optional<CameraMessage> generate() {
        try {
            Thread.sleep(sleep);
            LOGGER.debug("Random message generated");
            return Optional.of(new CameraMessage(rand.nextInt(cameraMax) + 1, randomLicense(), LocalDateTime.now()));
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        return Optional.empty();
    }


}
