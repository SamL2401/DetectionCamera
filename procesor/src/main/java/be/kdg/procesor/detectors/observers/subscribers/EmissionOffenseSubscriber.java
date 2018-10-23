package be.kdg.procesor.detectors.observers.subscribers;

import be.kdg.procesor.detectors.offenses.EmissionOffense;
import be.kdg.procesor.detectors.observers.publishers.CameraMessageEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * This is a subscriber and will listen to a new CameraMessageEvent
 * @author Sam Laureys
 */
@Component
public class EmissionOffenseSubscriber implements ApplicationListener<CameraMessageEvent> {
    private final EmissionOffense emissionOffense;

    public EmissionOffenseSubscriber(EmissionOffense emissionOffense) {
        this.emissionOffense = emissionOffense;
    }

    @Override
    public void onApplicationEvent(CameraMessageEvent event) {
        emissionOffense.detect(event.getCameraMessage());
    }
}
