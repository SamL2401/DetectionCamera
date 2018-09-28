package be.kdg.simulator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class MessagingConfig {

    @Bean
    public Queue cameraQueue(){
        return new Queue("camera-queue");
    }
}