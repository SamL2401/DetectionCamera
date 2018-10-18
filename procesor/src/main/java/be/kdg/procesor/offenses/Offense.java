package be.kdg.procesor.offenses;

import be.kdg.procesor.model.messages.CameraMessage;

public interface Offense {
    void detect(CameraMessage cm);
}
