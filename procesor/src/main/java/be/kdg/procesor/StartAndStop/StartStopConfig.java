package be.kdg.procesor.StartAndStop;

import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * This is a configuration file for the web
 *
 * @author Sam Laureys
 */
@Configuration
public class StartStopConfig {
    @Bean
    public RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry() {
        return new RabbitListenerEndpointRegistry();
    }
}