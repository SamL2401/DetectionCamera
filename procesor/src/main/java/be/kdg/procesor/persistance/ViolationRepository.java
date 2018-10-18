package be.kdg.procesor.persistance;

import be.kdg.procesor.model.violations.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViolationRepository extends JpaRepository<Violation,Long> {
    List<Violation> findByFine(Double fine);
}
