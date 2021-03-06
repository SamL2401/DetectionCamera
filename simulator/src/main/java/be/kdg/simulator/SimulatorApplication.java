package be.kdg.simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Simulator for the processor of the DetectionCamera
 *
 * @author Sam Laureys
 */
@SpringBootApplication
@EnableScheduling
public class SimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimulatorApplication.class, args);

    }
}
