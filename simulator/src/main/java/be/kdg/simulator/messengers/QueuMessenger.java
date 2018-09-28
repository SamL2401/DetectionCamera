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
        if (message == null)
            System.exit(0);
        template.convertAndSend(queue.getName(), message.toString());
    }
}