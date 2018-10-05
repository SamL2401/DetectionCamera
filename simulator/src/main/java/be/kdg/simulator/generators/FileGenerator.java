package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import be.kdg.simulator.reader.MessageFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "file")
public class FileGenerator implements MessageGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);

    private MessageFileReader messageFileReader;
    private List<CameraMessage> cameraMessages;
    private int messageIndex;

    public FileGenerator(MessageFileReader messageFileReader) {
        this.messageFileReader = messageFileReader;
        cameraMessages = new ArrayList<>();
        cameraMessages = messageFileReader.parseCameraMessageList();
    }

    @Override
    public CameraMessage generate() {
        if (messageIndex >= cameraMessages.size()) {
            LOGGER.info("All camera messages have been read, the program is closing");
            System.exit(0);
        }
        return cameraMessages.get(messageIndex++);
    }
}
