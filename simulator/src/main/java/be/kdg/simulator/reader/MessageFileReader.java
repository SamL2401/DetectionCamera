package be.kdg.simulator.reader;

import be.kdg.simulator.model.CameraMessage;

import java.util.List;

public interface MessageFileReader {
    List<CameraMessage> parseCameraMessageList();

    List<CameraMessage> parseCameraMessageList(char separator);
}
