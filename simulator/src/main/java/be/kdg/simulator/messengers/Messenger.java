package be.kdg.simulator.messengers;


import be.kdg.simulator.model.CameraMessage;

/**
 * Interface for Messengers
 * @author Sam Laureys
 * @version 1.0
 */
public interface Messenger {
    void sendMessage(CameraMessage cameraMessage);
}
