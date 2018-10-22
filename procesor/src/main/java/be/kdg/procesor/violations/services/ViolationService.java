package be.kdg.procesor.violations.services;

import be.kdg.procesor.violations.exceptions.ViolationException;
import be.kdg.procesor.violations.model.violations.Violation;
import be.kdg.procesor.violations.persistence.ViolationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
}

