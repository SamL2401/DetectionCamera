package be.kdg.procesor.detectors.offenses.buffers;

import be.kdg.procesor.messages.model.messages.CameraMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This is class is responsible for storing CameraMessages and removing CameraMessages older than a given time
 *
 * @author Sam Laureys
 */
@Component
@EnableScheduling
public class SpeedingCameraMessagesBuffer {

    private List<CameraMessage> cameraMessages;

    public SpeedingCameraMessagesBuffer() {
        this.cameraMessages = new ArrayList<CameraMessage>();
    }

    public void addCameraMessage(CameraMessage cameraMessage) {
        cameraMessages.add(cameraMessage);
    }

    @Scheduled(fixedDelay = 60000)
    public void UpdateTimeFrame() {
        //TODO from proprerties with @Value
        LocalDateTime time = LocalDateTime.now().minusMinutes(30);
        cameraMessages.removeIf(c -> c.getTimestamp().isBefore(time));
    }

    public List<CameraMessage> getCameraMessages() {
        return cameraMessages;
    }

    public Optional<List<CameraMessage>> getCameraMessage(String licensePlate) {
        Optional<List<CameraMessage>> optionalCameraMessages = Optional.empty();
        List<CameraMessage> cameraMessagesMatch = new ArrayList<>();
        for (CameraMessage cameraMessage : cameraMessages) {
            if (cameraMessage.getLicensePlate().equals(licensePlate)) {
                cameraMessagesMatch.add(cameraMessage);
            }
        }
        if (cameraMessagesMatch.size() > 0) {
            optionalCameraMessages = Optional.of(cameraMessagesMatch);
        }
        return optionalCameraMessages;
    }

    public void setCameraMessages(List<CameraMessage> cameraMessages) {
        this.cameraMessages = cameraMessages;
    }
}
