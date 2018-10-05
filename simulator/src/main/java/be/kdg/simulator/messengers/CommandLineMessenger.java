package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.FileGenerator;
import be.kdg.simulator.model.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "messenger.type", havingValue = "cmdline")
public class CommandLineMessenger implements Messenger {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);

    @Override
    public void sendMessage(CameraMessage cameraMessage) {
        LOGGER.debug("CommandLine outprint");
        System.out.println(cameraMessage);
    }
}
