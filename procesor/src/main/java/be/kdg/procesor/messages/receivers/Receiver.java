package be.kdg.procesor.messages.receivers;

/**
 * Interface for Receivers
 * @author Sam Laureys
 * @version 1.0
 */
public interface Receiver {
    void receive(String in) throws InterruptedException;
}
