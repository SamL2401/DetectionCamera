package be.kdg.procesor.offenses;

import be.kdg.procesor.model.CameraMessage;

public interface Offense {
    void detect(CameraMessage cm);
}
