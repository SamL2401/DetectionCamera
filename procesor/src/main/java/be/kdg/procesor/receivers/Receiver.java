package be.kdg.procesor.receivers;

public interface Receiver {
    void receive(String in) throws InterruptedException;
}
