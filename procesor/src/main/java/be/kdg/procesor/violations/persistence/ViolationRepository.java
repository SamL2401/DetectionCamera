package be.kdg.procesor.violations.persistence;

import be.kdg.procesor.violations.model.violations.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ViolationRepository extends JpaRepository<Violation,Long> {
    List<Violation> findAllByDateBetween(LocalDateTime start, LocalDateTime end);
}