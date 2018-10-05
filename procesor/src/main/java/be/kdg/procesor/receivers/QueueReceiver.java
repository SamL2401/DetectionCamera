package be.kdg.procesor.receivers;

import be.kdg.procesor.model.CameraMessage;
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
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RabbitListener(queues = "camera-queue")
public class QueueReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueReceiver.class);

    private final CameraServiceUtility cameraServiceUtility;
    private Offense offense;

    public QueueReceiver(Offense offense,CameraServiceUtility cameraServiceUtility) {
        this.offense = offense;
        this.cameraServiceUtility = cameraServiceUtility;
    }


    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        XmlMapper mapper = new XmlMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        mapper.registerModule(javaTimeModule);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try {
            CameraMessage cameraMessage = mapper.readValue(in, CameraMessage.class);
            LOGGER.info("Message received: " + cameraMessage);
            offense.detect(cameraMessage);
        } catch (IOException e) {
            LOGGER.error("Failed to parse queue message to xml");
        }

        Thread.sleep(1000);
    }
}
