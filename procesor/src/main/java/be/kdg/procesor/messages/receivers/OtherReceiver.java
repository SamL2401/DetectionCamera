package be.kdg.procesor.messages.receivers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for receiving messages somewhere
 *
 * @author Sam Laureys
 */
@Component
@ConditionalOnProperty(name = "receiver.type", havingValue = "other")
public class OtherReceiver implements Receiver {
    @Override
    public void receive(String in) throws InterruptedException {

    }
}
