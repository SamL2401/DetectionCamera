package be.kdg.procesor.violations.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is a configuration file for the web
 * @author Sam Laureys
 */
@Configuration
public class WebConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
