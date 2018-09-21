package be.kdg.procesor.messengers;

import be.kdg.procesor.generators.MessageGenerator;
import be.kdg.procesor.model.CameraMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
//oplossen met
// a) @Qualifier  in de source
// b) @ConditionalOnProperty  in de application.properties
public class CommandLineMessenger implements Messenger {

    private final MessageGenerator messageGenerator;

    // Beste zelf, dit is Constructor Injection en dit is niet meer nodig @autowired
    public CommandLineMessenger(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    @Override
    public void sendMessage() {
        System.out.println(messageGenerator.generate());

    }
}
