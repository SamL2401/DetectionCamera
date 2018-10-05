package be.kdg.simulator.messengers;

import be.kdg.simulator.model.CameraMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandLineMessengerTest {

    CameraMessage cameraMessage = new CameraMessage(1, "1-ABA-111", LocalDateTime.now());
    @Autowired
    private Messenger messenger;

    @Test
    public void sendMessage() {
        messenger.sendMessage(cameraMessage);
    }
}