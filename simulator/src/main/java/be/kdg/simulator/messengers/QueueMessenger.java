package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.FileGenerator;
import be.kdg.simulator.model.CameraMessage;
import be.kdg.simulator.toXml.MessageToXml;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for sending CameraMessages to the Queue
 * @author Sam Laureys
 * @version 1.03
 */
@Component
@ConditionalOnProperty(name = "messenger.type", havingValue = "queue")
public class QueueMessenger implements Messenger {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);

    private final RabbitTemplate template;
    private final Queue queue;
    private final MessageToXml messageToXml;

    public QueueMessenger(RabbitTemplate template, Queue queue, MessageToXml messageToXml) {
        this.template = template;
        this.queue = queue;
        this.messageToXml = messageToXml;
    }

    @Override
    public void sendMessage(CameraMessage cameraMessage) {
        try {
            LOGGER.info("Sending message to the RabbitMq queue: " + cameraMessage.toString() + " to the " + queue.getName());
            String cameraMessageXml = messageToXml.toXml(cameraMessage);
            template.convertAndSend(queue.getName(), cameraMessageXml);
        } catch (JsonProcessingException e) {
            LOGGER.error("error converting camera message as XML!");
        }
    }
}