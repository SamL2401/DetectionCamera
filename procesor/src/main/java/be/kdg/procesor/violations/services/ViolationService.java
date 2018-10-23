package be.kdg.procesor.violations.services;

import be.kdg.procesor.violations.exceptions.ViolationException;
import be.kdg.procesor.violations.model.Violation;
import be.kdg.procesor.violations.persistence.ViolationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * This class is the service layer of the violations
 *
 * @author Sam Laureys
 */
@Service
@Transactional
public class ViolationService {
    private final ViolationRepository violationRepository;

    public ViolationService(ViolationRepository violationRepository) {
        this.violationRepository = violationRepository;
    }

    public Violation save(Violation violation) {
        return violationRepository.save(violation);
    }

    public Violation load(Long id) throws ViolationException {
        Optional<Violation> optionalViolation = violationRepository.findById(id);
        if (optionalViolation.isPresent()) {
            return optionalViolation.get();
        }
        throw new ViolationException("Violation not found");
    }

    public List<Violation> getViolationsList() throws ViolationException {
        List<Violation> optionalViolationList = violationRepository.findAll();
        if (optionalViolationList.size() > 0)
            return optionalViolationList;
        throw new ViolationException("Violations list not found");
    }

    public Violation add(Violation violation) {
        return violationRepository.save(violation);
    }

    public List<Violation> getFilteredViolations(LocalDateTime startTime, LocalDateTime endTime) throws ViolationException {
        List<Violation> optionalViolationList = violationRepository.findAllByTimestampBetween(startTime, endTime);
        if (optionalViolationList.size() > 0)
            return optionalViolationList;

        throw new ViolationException("Filtered Violations list not found");
    }

    public Violation setApproveViolation(Long id, boolean approved) throws ViolationException {
        Violation violation = load(id);
        violation.setApproved(approved);

        return violation;
    }

    public Violation setAmountViolation(Long id, double fine, String motivation) throws ViolationException {
        Violation Violation = load(id);
        Violation.setFine(fine);
        Violation.setMotivation(motivation);

        return Violation;
    }
}

