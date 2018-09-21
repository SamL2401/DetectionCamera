package be.kdg.procesor.generators;

import be.kdg.procesor.model.CameraMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RandomMessageGenerator implements MessageGenerator {
    @Override
    public CameraMessage generate() {
        return new CameraMessage(1,"1-EYE-957", LocalDate.now());
    }
}
