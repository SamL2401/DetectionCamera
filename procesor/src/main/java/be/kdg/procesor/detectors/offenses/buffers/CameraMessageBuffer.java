package be.kdg.procesor.detectors.offenses.buffers;

import be.kdg.procesor.detectors.observers.publishers.CameraMessagePublisher;
import be.kdg.procesor.messages.failsLoggers.LogToFile;
import be.kdg.procesor.messages.model.messages.CameraMessage;
import be.kdg.procesor.settings.configs.SettingsProcessorConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final LogToFile logToFile;
    private final SettingsProcessorConfiguration settingsProcessorConfiguration;
    private Map<CameraMessage, Integer> cameraMessages;
    private long retryDelay;
    private int retryCount;

    public CameraMessageBuffer(CameraMessagePublisher cameraMessagePublisher, LogToFile logToFile, SettingsProcessorConfiguration settingsProcessorConfiguration) {
        this.cameraMessagePublisher = cameraMessagePublisher;
        this.logToFile = logToFile;
        this.settingsProcessorConfiguration = settingsProcessorConfiguration;
        retryCount = settingsProcessorConfiguration.getRetryCount();
        retryDelay = settingsProcessorConfiguration.getRetryDelay();
        cameraMessages = new ConcurrentHashMap<>();

    }

    public void addCameraMessage(CameraMessage cameraMessage) {
        retryCount = settingsProcessorConfiguration.getRetryCount();
        if (cameraMessages.containsKey(cameraMessage)) {
            if (cameraMessages.get(cameraMessage) > retryCount) {
                cameraMessages.put(cameraMessage, cameraMessages.get(cameraMessage) - retryCount);
                if (cameraMessages.get(cameraMessage) == retryCount) {
                    //Send to log or to Queue with new component
                    logToFile.saveToFile(cameraMessage);
                    LOGGER.warn("CameraMassage: " + cameraMessage + " logged after " + retryCount + " attempts!");
                }
            }
        } else {
            cameraMessages.put(cameraMessage, 0);
        }
    }

    @Scheduled(fixedDelay = 2000)
    private void retryCameraMessage() throws Exception {

        LOGGER.info("Retry failedCameraMessages");
        if (cameraMessages.size() > 0) {
            retryCount = settingsProcessorConfiguration.getRetryCount();
            retryDelay = settingsProcessorConfiguration.getRetryDelay();
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


