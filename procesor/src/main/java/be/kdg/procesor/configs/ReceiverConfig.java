package be.kdg.procesor.configs;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiverConfig {
    @Bean
    public Queue cameraQueue() {
        return new Queue("camera-queue");
    }
    @Bean
    public XmlMapper xmlMapper() {
        return new XmlMapper();
    }
    @Bean
    public JavaTimeModule javaTimeModule() {
        return new JavaTimeModule();
    }
}
