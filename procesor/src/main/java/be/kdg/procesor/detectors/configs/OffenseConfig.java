package be.kdg.procesor.detectors.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is a configuration file for the offense handling
 *
 * @author Sam Laureys
 */
@Configuration
public class OffenseConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
