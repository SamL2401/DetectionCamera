package be.kdg.procesor.model.cameras;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Segment {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private int connectedCameraId;
    @Column
    private int distance;
    @Column
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
