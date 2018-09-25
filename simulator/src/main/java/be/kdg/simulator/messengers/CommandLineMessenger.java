package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.MessageGenerator;
import ch.qos.logback.core.util.FixedDelay;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
//oplossen met
// a) @Qualifier  in de source
// b) @ConditionalOnProperty  in de application.properties

public class CommandLineMessenger implements Messenger {
    //!!!!!!cyclic dependency error: kan door constructor injection samen met field oplossing: er moet field injection gebruikt worden op een van de twee beans
    private final MessageGenerator messageGenerator;

    // Beste zelf, dit is Constructor Injection en dit is niet meer nodig @autowired
    public CommandLineMessenger(/*@Qualifier("randomMessageGenerator") */MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    @Override
    @Scheduled(fixedDelay = 1000L)
    public void sendMessage() {
        System.out.println(messageGenerator.generate());

    }
}
