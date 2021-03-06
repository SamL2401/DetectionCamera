package be.kdg.procesor.detectors.model.cameras;

import lombok.Data;

/**
 * This class represents a DetectionCamera
 *
 * @author Sam Laureys
 */
@Data
public class DetectionCamera {
    private int cameraId;
    private Location location;
    private Segment segment;
    private int euroNorm;

    public DetectionCamera() {
    }

    public DetectionCamera(int cameraId, Location location, int euroNorm) {
        this.cameraId = cameraId;
        this.location = location;
        this.euroNorm = euroNorm;
    }

    public DetectionCamera(int cameraId, Location location) {
        this.cameraId = cameraId;
        this.location = location;
    }

    public DetectionCamera(int cameraId, Location location, Segment segment) {
        this.cameraId = cameraId;
        this.location = location;
        this.segment = segment;
    }

    public DetectionCamera(int cameraId, Location location, Segment segment, int euroNorm) {
        this.cameraId = cameraId;
        this.location = location;
        this.segment = segment;
        this.euroNorm = euroNorm;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public int getEuroNorm() {
        return euroNorm;
    }

    public void setEuroNorm(int euroNorm) {
        this.euroNorm = euroNorm;
    }

    @Override
    public String toString() {
        return "DetectionCamera{" +
                "cameraId=" + cameraId +
                ", location=" + location +
                ", segment=" + segment +
                ", euroNorm=" + euroNorm +
                '}';
    }
}
