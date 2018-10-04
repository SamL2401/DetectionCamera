package be.kdg.procesor.receivers;

import be.kdg.procesor.model.CameraMessage;
import be.kdg.procesor.offenses.Offense;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RabbitListener(queues = "camera-queue")
public class QueueReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueReceiver.class);
    private Offense offense;

    public QueueReceiver(Offense offense) {
        this.offense = offense;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        CameraMessage cameraMessage = new CameraMessage();
        // String uitwerken naar CameraMessage
        Pattern pattern = Pattern.compile(" ([0-9]*) (.*) (.*)$");
        Matcher matcher = pattern.matcher(in);
        if (matcher.find())
        {
            cameraMessage.setId(Integer.parseInt(matcher.group(1)));
            cameraMessage.setLicensePlate(matcher.group(2));
            cameraMessage.setTimestamp(LocalDateTime.parse(matcher.group(3)));
            LOGGER.trace(cameraMessage.toString());
        } else LOGGER.error("mather not found");

        offense.detect(cameraMessage);
        Thread.sleep(3000);
    }
}
