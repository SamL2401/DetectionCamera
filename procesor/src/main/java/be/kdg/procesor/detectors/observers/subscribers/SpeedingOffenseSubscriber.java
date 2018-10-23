package be.kdg.procesor.detectors.observers.subscribers;

import be.kdg.procesor.detectors.observers.publishers.CameraMessageEvent;
import be.kdg.procesor.detectors.offenses.SpeedingOffense;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * This is a subscriber and will listen to a new CameraMessageEvent
 *
 * @author Sam Laureys
 */
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
