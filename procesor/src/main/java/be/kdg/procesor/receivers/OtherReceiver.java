package be.kdg.procesor.receivers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "receiver.type", havingValue = "other")
public class OtherReceiver implements Receiver{
    @Override
    public void receive(String in) throws InterruptedException {

    }
}
