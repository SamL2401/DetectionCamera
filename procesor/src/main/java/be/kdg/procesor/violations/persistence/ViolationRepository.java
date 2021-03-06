package be.kdg.procesor.violations.persistence;

import be.kdg.procesor.violations.model.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * This interface is the repository for all the violations
 *
 * @author Sam Laureys
 */
public interface ViolationRepository extends JpaRepository<Violation, Long> {
    List<Violation> findAllByTimestampBetween(LocalDateTime start, LocalDateTime end);

    Optional<Violation> findByLicensePlateAndTimestampBetweenAndViolationType(String licensePlate, LocalDateTime start, LocalDateTime end, String violationType);
}