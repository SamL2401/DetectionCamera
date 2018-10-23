package be.kdg.procesor.detectors.model.cameras;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class represents a Segment
 * @author Sam Laureys
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Segment {
    private Long id;
    private int connectedCameraId;
    private int distance;
    private int speedLimit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getConnectedCameraId() {
        return connectedCameraId;
    }

    public void setConnectedCameraId(int connectedCameraId) {
        this.connectedCameraId = connectedCameraId;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }
}
