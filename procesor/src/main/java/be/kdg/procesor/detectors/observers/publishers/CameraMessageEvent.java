package be.kdg.procesor.detectors.observers.publishers;

import be.kdg.procesor.messages.model.messages.CameraMessage;
import org.springframework.context.ApplicationEvent;

/**
 * This class models a CameraMessageEvent as a ApplicationEvent
 * @author Sam Laureys
 */
public class CameraMessageEvent extends ApplicationEvent {
    private CameraMessage cameraMessage;

    public CameraMessageEvent(Object source, CameraMessage cameraMessage) {
        super(source);
        this.cameraMessage = cameraMessage;
    }

    public CameraMessage getCameraMessage() {
        return cameraMessage;
    }

}
