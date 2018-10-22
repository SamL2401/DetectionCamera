package be.kdg.procesor.detectors.observers.subscribers;

import be.kdg.procesor.detectors.offenses.SpeedingOffense;
import be.kdg.procesor.detectors.observers.publishers.CameraMessageEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SpeedingOffenseSubscriber implements ApplicationListener<CameraMessageEvent> {
    private final SpeedingOffense speedingOffense;

    public SpeedingOffenseSubscriber(SpeedingOffense speedingOffense) {
        this.speedingOffense = speedingOffense;
    }

    @Override
    public void onApplicationEvent(CameraMessageEvent event) {
        //speedingOffense detect something
    }
}
