package be.kdg.procesor.messages.receivers;

public interface Receiver {
    void receive(String in) throws InterruptedException;
}
