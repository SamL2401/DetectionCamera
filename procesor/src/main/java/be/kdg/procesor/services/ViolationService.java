package be.kdg.procesor.services;

import be.kdg.procesor.exceptions.ViolationException;
import be.kdg.procesor.model.violations.Violation;
import be.kdg.procesor.persistance.ViolationRepository;
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

