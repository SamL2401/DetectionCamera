package be.kdg.procesor.messages.model.messages;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class represents a CameraMessage
 *
 * @author Sam Laureys
 */
public class CameraMessage {
    private int id;
    private String licensePlate;
    private LocalDateTime timestamp;

    public CameraMessage() {

    }

    public CameraMessage(int id, String licensePlate, LocalDateTime timestamp) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CameraMessage that = (CameraMessage) o;
        return id == that.id &&
                Objects.equals(licensePlate, that.licensePlate) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licensePlate, timestamp);
    }

    @Override
    public String toString() {
        return String.format("camera Message %d %s %s", id, licensePlate, timestamp);
    }
}
