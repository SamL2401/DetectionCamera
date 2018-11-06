package be.kdg.procesor.detectors.offenses.buffers;

import be.kdg.procesor.detectors.observers.publishers.CameraMessagePublisher;
import be.kdg.procesor.messages.model.messages.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@EnableScheduling
public class CameraMessageBuffer {
    private final Logger LOGGER = LoggerFactory.getLogger(CameraMessageBuffer.class);

    private final CameraMessagePublisher cameraMessagePublisher;
    private Map<CameraMessage, Integer> cameraMessages;
    @Value("${retryCount}")
    private int retryCount;

    public CameraMessageBuffer(CameraMessagePublisher cameraMessagePublisher) {
        this.cameraMessagePublisher = cameraMessagePublisher;
        cameraMessages = new ConcurrentHashMap<>();
    }

    public void addCameraMessage(CameraMessage cameraMessage) {
        if (cameraMessages.containsKey(cameraMessage)) {
            if (cameraMessages.get(cameraMessage) > retryCount) {
                cameraMessages.put(cameraMessage, cameraMessages.get(cameraMessage) - retryCount);
                if (cameraMessages.get(cameraMessage) == retryCount) {
                    //Send to log or to Queue
                    LOGGER.warn("CameraMassage: " + cameraMessage + " logged after " + retryCount + " attempts!");
                    //cameraMessages.remove(cameraMessage);
                }
            }
        } else {
            cameraMessages.put(cameraMessage, 0);
        }
    }

    @Scheduled(fixedDelayString = "${retryDelay}")
    private void retryCameraMessage() {
        System.out.println("retry");
        if (cameraMessages.size() > 0) {
            List<CameraMessage> toRetryMessages = new ArrayList<>();
            for (Map.Entry<CameraMessage, Integer> entry : cameraMessages.entrySet()) {
                if (entry.getValue() < retryCount) {
                    entry.setValue(entry.getValue() + retryCount + 1);
                    toRetryMessages.add(entry.getKey());
                }
            }
            for (CameraMessage toRetryMessage : toRetryMessages) {
                cameraMessagePublisher.publish(toRetryMessage);
            }



        }
    }

}


