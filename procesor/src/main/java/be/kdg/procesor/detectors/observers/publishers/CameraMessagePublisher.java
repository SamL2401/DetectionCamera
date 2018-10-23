package be.kdg.procesor.detectors.observers.publishers;

import be.kdg.procesor.messages.model.messages.CameraMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Publishes an CameraMessageEvent So that subscribers can react
 * @author Sam Laureys
 */
@Component
public class CameraMessagePublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(final CameraMessage cameraMessage) {
        CameraMessageEvent cameraMessageEvent = new CameraMessageEvent(this, cameraMessage);
        applicationEventPublisher.publishEvent(cameraMessageEvent);
    }
}
