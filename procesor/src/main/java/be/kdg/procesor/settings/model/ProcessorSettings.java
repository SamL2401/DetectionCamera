package be.kdg.procesor.settings.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class represents the settings for the processor
 *
 * @author Sam Laureys
 */
@Entity
public class ProcessorSettings {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private double emissionFineFactor;
    @Column
    private double speedingFineFactor;
    @Column
    private long timeFrameBufferingSegments;
    @Column
    private long timeFrameDoubleEmission;
    @Column
    private long retryDelay;
    @Column
    private int retryCount;

    public ProcessorSettings() {
    }

    public ProcessorSettings(double emissionFineFactor, double speedingFineFactor, long timeFrameBufferingSegments, long timeFrameDoubleEmission, long retryDelay, int retryCount) {
        this.emissionFineFactor = emissionFineFactor;
        this.speedingFineFactor = speedingFineFactor;
        this.timeFrameBufferingSegments = timeFrameBufferingSegments;
        this.timeFrameDoubleEmission = timeFrameDoubleEmission;
        this.retryDelay = retryDelay;
        this.retryCount = retryCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getEmissionFineFactor() {
        return emissionFineFactor;
    }

    public void setEmissionFineFactor(double emissionFineFactor) {
        this.emissionFineFactor = emissionFineFactor;
    }

    public double getSpeedingFineFactor() {
        return speedingFineFactor;
    }

    public void setSpeedingFineFactor(double speedingFineFactor) {
        this.speedingFineFactor = speedingFineFactor;
    }

    public long getTimeFrameBufferingSegments() {
        return timeFrameBufferingSegments;
    }

    public void setTimeFrameBufferingSegments(long timeFrameBufferingSegments) {
        this.timeFrameBufferingSegments = timeFrameBufferingSegments;
    }

    public long getTimeFrameDoubleEmission() {
        return timeFrameDoubleEmission;
    }

    public void setTimeFrameDoubleEmission(long timeFrameDoubleEmission) {
        this.timeFrameDoubleEmission = timeFrameDoubleEmission;
    }

    public long getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(long retryDelay) {
        this.retryDelay = retryDelay;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}
