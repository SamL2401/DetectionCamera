package be.kdg.procesor.settings.dto;

/**
 * this class represents the DTO for the processor settings
 */
public class ProcessorSettingsDTO {

    private Long id;
    private double emissionFineFactor;
    private double speedingFineFactor;
    private long timeFrameBufferingSegments;
    private long timeFrameDoubleEmission;
    private long retryDelay;
    private int retryCount;

    public ProcessorSettingsDTO() {
    }

    public ProcessorSettingsDTO(double emissionFineFactor, double speedingFineFactor, long timeFrameBufferingSegments, long timeFrameDoubleEmission, long retryDelay, int retryCount) {
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
