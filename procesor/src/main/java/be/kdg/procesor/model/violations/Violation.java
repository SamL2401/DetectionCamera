package be.kdg.procesor.model.violations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
//    @Column
//    private String licensePlate;
//    @Column
//    private String Rijksrigister;
    @Column
    private LocalDateTime date;
    @Column
    private boolean approved;
    @Column
    private String motivation;

    public Violation(Double fine, LocalDateTime date, boolean approved, String motivation) {
        this.fine = fine;
        this.date = date;
        this.approved = approved;
        this.motivation = motivation;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
        return "Violation{" +
                "id=" + id +
                ", fine=" + fine +
                ", date=" + date +
                ", approved=" + approved +
                ", motivation='" + motivation + '\'' +
                '}';
    }
}
