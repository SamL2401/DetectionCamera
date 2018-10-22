package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;

import java.util.Optional;

/**
 * Interface for Generators
 * @author Sam Laureys
 * @version 1.0
 */
public interface MessageGenerator {
    Optional<CameraMessage> generate();
}
