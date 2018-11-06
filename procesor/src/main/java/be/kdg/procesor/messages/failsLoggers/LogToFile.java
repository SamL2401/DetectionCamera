package be.kdg.procesor.messages.failsLoggers;

import be.kdg.procesor.messages.converters.MessageXmlConverter;
import be.kdg.procesor.messages.model.messages.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class LogToFile {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogToFile.class);

    private final MessageXmlConverter messageXmlConverter;
    private File file;

    public LogToFile(MessageXmlConverter messageXmlConverter) {
        this.messageXmlConverter = messageXmlConverter;
        try {
            file = new File("CameraMessageFails.txt");
            if (file.createNewFile()) {
                LOGGER.info("file created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveToFile(CameraMessage cameraMessage) {
        try {
            FileWriter writer = new FileWriter(file.getAbsoluteFile(), true);
            writer.write(System.lineSeparator());
            writer.write(messageXmlConverter.toXml(cameraMessage));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.warn("Failed to write to file.");
        }

    }

}
