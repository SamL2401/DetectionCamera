package be.kdg.procesor.receivers;

import be.kdg.procesor.messageHandlers.XmlToMessage;
import be.kdg.procesor.model.messages.CameraMessage;
import be.kdg.procesor.offenses.Offense;
import be.kdg.procesor.services.CameraServiceUtility;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RabbitListener(queues = "camera-queue")
@ConditionalOnProperty(name = "receiver.type", havingValue = "queue")
public class QueueReceiver implements Receiver{
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueReceiver.class);

    private final Offense offense;
    private final XmlToMessage xmlToMessage;

    public QueueReceiver(Offense offense, XmlToMessage xmlToMessage) {
        this.offense = offense;
        this.xmlToMessage = xmlToMessage;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        try {
            CameraMessage cameraMessage = xmlToMessage.toMessage(in);
            LOGGER.info("Message received: " + cameraMessage);
            offense.detect(cameraMessage);
        } catch (IOException e) {
            LOGGER.error("Failed to parse queue xml to message");
        }
        Thread.sleep(10000000);
    }
}
