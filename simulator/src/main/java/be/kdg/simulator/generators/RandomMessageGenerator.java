package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@ConditionalOnProperty(name="generator.type",havingValue = "random")
public class RandomMessageGenerator implements MessageGenerator {
    private int id = 0;
    private String licensePlate = "";
    @Override
    public CameraMessage generate() {
        return new CameraMessage(id++,"1-EYE-957", LocalDate.now());
    }
}
