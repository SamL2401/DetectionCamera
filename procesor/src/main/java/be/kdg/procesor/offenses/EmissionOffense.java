package be.kdg.procesor.offenses;

import be.kdg.procesor.model.CameraMessage;
import be.kdg.procesor.receivers.QueueReceiver;
import org.springframework.stereotype.Component;

@Component
public class EmissionOffense implements Offense {
    @Override
    public void detect(CameraMessage cm) {
        System.out.println(cm.toString());
    }
}
