package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.MessageGenerator;
import be.kdg.simulator.model.CameraMessage;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;


@Component
@ConditionalOnProperty(name = "messenger.type", havingValue = "queue")
public class QueuMessenger implements Messenger {

    private final MessageGenerator messageGenerator;
    private final RabbitTemplate template;
    private final Queue queue;

    public QueuMessenger(MessageGenerator messageGenerator, RabbitTemplate template, Queue queue) {
        this.messageGenerator = messageGenerator;
        this.template = template;
        this.queue = queue;
    }
/*
    @Bean
    @Schedules({@Scheduled(fixedDelayString = "#{${randomMessage.fixedDelay.in.milliseconds}}"),
            @Scheduled(cron = "${randomMessage.rushHour1.cron.expression}"),
            @Scheduled(cron = "${randomMessage.rushHour2.cron.expression}")})
    @ConditionalOnProperty(name = "generator.type", havingValue = "random")
    public void sendRandomMessages() {
        sendMessage();
    }

    @Scheduled(fixedDelay = 1000L)
    @ConditionalOnProperty(name = "generator.type", havingValue = "file")
    public void sendFileMessages() {
        sendMessage();
    }
*/
    @Override
    @Scheduled(fixedDelayString = "${frequentie.val}")
    public void sendMessage() {
        CameraMessage message = messageGenerator.generate();
        //uitwerken voor doorsturen uml
        /*@Override
        public void sendMessage(CameraMessage cameraMessage) {
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

*/
        if (message == null)
            System.exit(0);
        template.convertAndSend(queue.getName(), message.toString());
    }
}