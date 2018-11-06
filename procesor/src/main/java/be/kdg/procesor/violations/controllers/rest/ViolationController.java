package be.kdg.procesor.violations.controllers.rest;

import be.kdg.procesor.violations.dto.ViolationDTO;
import be.kdg.procesor.violations.exceptions.ViolationException;
import be.kdg.procesor.violations.model.Violation;
import be.kdg.procesor.violations.services.ViolationService;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling the REST calls from violations
 *
 * @author Sam Laureys
 * @version 1.01
 */
@RestController
@RequestMapping("/api")
public class ViolationController {
    private final ViolationService violationService;
    private final ModelMapper modelMapper;

    public ViolationController(ViolationService violationService, ModelMapper modelMapper) {
        this.violationService = violationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("violations/{id}")
    public ResponseEntity<ViolationDTO> loadViolation(@PathVariable Long id) throws ViolationException {
        Violation violation = violationService.load(id);
        return new ResponseEntity<>(modelMapper.map(violation, ViolationDTO.class), HttpStatus.OK);
    }

    @PostMapping("/violations")
    public ResponseEntity<ViolationDTO> createViolation(@RequestBody ViolationDTO violationDTO) {
        Violation violationIn = modelMapper.map(violationDTO, Violation.class);
        Violation violationOut = violationService.save(violationIn);
        return new ResponseEntity<>(modelMapper.map(violationOut, ViolationDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/violations/{id}")
    public ResponseEntity<ViolationDTO> updateViolation(@PathVariable Long id, @RequestParam("fine") Double newFine) throws ViolationException {
        Violation violationIn = violationService.load(id);
        violationIn.setFine(newFine); //beter in service layer
        Violation violationOut = violationService.save(violationIn);
        return new ResponseEntity<>(modelMapper.map(violationOut, ViolationDTO.class), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/violations/filtered")
    public ResponseEntity<List<ViolationDTO>> loadFilteredViolations(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) throws ViolationException {
        List<Violation> violations = violationService.getFilteredViolations(startTime, endTime);
        List<ViolationDTO> violationDTOs = new ArrayList<>();
        for (Violation violation : violations) {
            violationDTOs.add(modelMapper.map(violation, ViolationDTO.class));
        }
        return new ResponseEntity<>(violationDTOs, HttpStatus.OK);
    }

    @PutMapping(path = "/violations/approve/{id}")
    public ResponseEntity<ViolationDTO> putApproveViolation(@PathVariable Long id, @RequestParam("approved") boolean approved) throws ViolationException {
        Violation violationIn = violationService.setApproveViolation(id, approved);
        Violation violationOut = violationService.save(violationIn);
        return new ResponseEntity<>(modelMapper.map(violationOut, ViolationDTO.class), HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/violations/amount/{id}")
    public ResponseEntity<ViolationDTO> putChangeAmountViolation(@PathVariable Long id,
                                                                 @RequestParam("amount") int amount,
                                                                 @RequestParam("motivation") String motivation) throws ViolationException {
        Violation violationIn = violationService.setAmountViolation(id, amount, motivation);
        Violation violationOut = violationService.save(violationIn);

        return new ResponseEntity<>(modelMapper.map(violationOut, ViolationDTO.class), HttpStatus.ACCEPTED);
    }
}
