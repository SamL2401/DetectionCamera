package be.kdg.simulator.simulator;

import be.kdg.simulator.generators.FileGenerator;
import be.kdg.simulator.generators.MessageGenerator;
import be.kdg.simulator.messengers.Messenger;
import be.kdg.simulator.model.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * This class is responsible for delegating messages from the generator to the messenger
 * @author Sam Laureys
 * @version 1.03
 */
@Component
public class MessageSimulator implements Simulator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);
    private final MessageGenerator messageGenerator;
    private final Messenger messenger;

    public MessageSimulator(MessageGenerator messageGenerator, Messenger messenger) {
        this.messageGenerator = messageGenerator;
        this.messenger = messenger;
    }

    @Override
    @PostConstruct
    public void simulate() {
        Optional<CameraMessage> opt = messageGenerator.generate();
        while (opt.isPresent()) {
            LOGGER.debug("Delegate message to messenger");
            messenger.sendMessage(opt.get());
            opt = messageGenerator.generate();
        }
    }
}
