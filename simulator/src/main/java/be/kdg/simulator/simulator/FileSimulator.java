package be.kdg.simulator.simulator;

import be.kdg.simulator.generators.FileGenerator;
import be.kdg.simulator.generators.MessageGenerator;
import be.kdg.simulator.messengers.Messenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "file")
public class FileSimulator implements Simulator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);
    private MessageGenerator messageGenerator;
    private Messenger messenger;

    public FileSimulator(MessageGenerator messageGenerator, Messenger messenger) {
        this.messageGenerator = messageGenerator;
        this.messenger = messenger;
    }

    @Override
    @Schedules({@Scheduled(fixedDelayString = "#{${randomMessage.fixedDelay.in.milliseconds}}"),
            @Scheduled(cron = "${randomMessage.rushHour1.cron.expression}"),
            @Scheduled(cron = "${randomMessage.rushHour2.cron.expression}")})
    public void simulate() {
        LOGGER.debug("Delegate random message to messenger");
        messenger.sendMessage(messageGenerator.generate());
    }

}
