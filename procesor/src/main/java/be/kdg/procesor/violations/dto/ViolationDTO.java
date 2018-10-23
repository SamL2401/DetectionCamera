package be.kdg.procesor.violations.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * This class is a data transfer object from a violation
 *
 * @author Sam Laureys
 */
@Data
public class ViolationDTO {
    @NotEmpty
    private Double fine;
    private String violationType;
    private boolean approved;
    private String motivation;
    private String licensePlate;
    private LocalDateTime timestamp;

    public ViolationDTO() {
    }

    public ViolationDTO(@NotEmpty Double fine, String violationType, boolean approved, String motivation) {
        this.fine = fine;
        this.violationType = violationType;
        this.approved = approved;
        this.motivation = motivation;
    }

    public ViolationDTO(@NotEmpty Double fine, String violationType, boolean approved, String motivation, String licensePlate, LocalDateTime timestamp) {
        this.fine = fine;
        this.violationType = violationType;
        this.approved = approved;
        this.motivation = motivation;
        this.licensePlate = licensePlate;
        this.timestamp = timestamp;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
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
}
