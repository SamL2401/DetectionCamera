package be.kdg.procesor.detectors.observers.subscribers;

import be.kdg.procesor.detectors.offenses.EmissionOffense;
import be.kdg.procesor.detectors.observers.publishers.CameraMessageEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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
