package be.kdg.procesor.messages.receivers;

import be.kdg.procesor.detectors.observers.publishers.CameraMessagePublisher;
import be.kdg.procesor.messages.messageHandlers.XmlToMessage;
import be.kdg.procesor.messages.model.messages.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * This class is responsible for receiving messages from the queue
 *
 * @author Sam Laureys
 * @version 1.01
 */
@Component
@RabbitListener(queues = "camera-queue")
@ConditionalOnProperty(name = "receiver.type", havingValue = "queue")
public class QueueReceiver implements Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueReceiver.class);

    private final CameraMessagePublisher cameraMessagePublisher;
    private final XmlToMessage xmlToMessage;

    public QueueReceiver(CameraMessagePublisher cameraMessagePublisher, XmlToMessage xmlToMessage) {
        this.cameraMessagePublisher = cameraMessagePublisher;
        this.xmlToMessage = xmlToMessage;
    }

    @RabbitHandler
    public void receive(String in) {
        try {
            CameraMessage cameraMessage = xmlToMessage.toMessage(in);
            LOGGER.info("Message received: " + cameraMessage);
            cameraMessagePublisher.publish(cameraMessage);
        } catch (IOException e) {
            LOGGER.error("Failed to parse queue xml to message");
        }
    }
}
