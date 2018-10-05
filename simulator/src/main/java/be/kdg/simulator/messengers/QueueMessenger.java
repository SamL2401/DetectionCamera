package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.FileGenerator;
import be.kdg.simulator.model.CameraMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
@ConditionalOnProperty(name = "messenger.type", havingValue = "queue")
public class QueueMessenger implements Messenger {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);

    private final RabbitTemplate template;
    private final Queue queue;

    public QueueMessenger(RabbitTemplate template, Queue queue) {
        this.template = template;
        this.queue = queue;
    }

    @Override
    public void sendMessage(CameraMessage cameraMessage) {
        //uitwerking voor doorsturen xml
        LOGGER.info("Sending message to the RabbitMq queue: " + cameraMessage.toString() + " to the " + queue.getName());
        XmlMapper mapper = new XmlMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        mapper.registerModule(javaTimeModule);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try {
            String cameraMessageXml = mapper.writeValueAsString(cameraMessage);
            template.convertAndSend(queue.getName(), cameraMessageXml);
        } catch (JsonProcessingException e) {
            LOGGER.error("error converting camera message as XML!");
        }
    }
}