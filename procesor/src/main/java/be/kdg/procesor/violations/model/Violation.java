package be.kdg.procesor.violations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * This class represents a Violation
 *
 * @author Sam Laureys
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Violation {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Double fine;
    @Column
    private String violationType;
    @Column
    private String licensePlate;
    //    @Column
//    private String nationalRegister;
    @Column
    private LocalDateTime timestamp;
    @Column
    private boolean approved;
    @Column
    private String motivation;

    public Violation() {

    }

    public Violation(Double fine, LocalDateTime timestamp, boolean approved) {
        this.fine = fine;
        this.timestamp = timestamp;
        this.approved = approved;
    }

    public Violation(Double fine, String violationType, String licensePlate, LocalDateTime timestamp) {
        this.fine = fine;
        this.violationType = violationType;
        this.licensePlate = licensePlate;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Violation " + violationType +
                " on " + timestamp +
                " with " + licensePlate +
                " has a fine of " + fine +
                "dollars";
    }
}
