package be.kdg.procesor.controllers.rest;

import be.kdg.procesor.dto.ViolationDTO;
import be.kdg.procesor.exceptions.ViolationException;
import be.kdg.procesor.model.violations.Violation;
import be.kdg.procesor.services.ViolationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Violation violationIn = modelMapper.map(violationDTO,Violation.class);
        Violation violationOut = violationService.save(violationIn);
        return new ResponseEntity<>(modelMapper.map(violationOut,ViolationDTO.class),HttpStatus.CREATED);
    }

    @PutMapping("/violations/{id}")
    public ResponseEntity<ViolationDTO> updateViolation(@PathVariable Long id, @RequestParam("fine") Double newFine) throws ViolationException {
        Violation violationIn = violationService.load(id);
        violationIn.setFine(newFine); //beter in service layer
        Violation violationOut = violationService.save(violationIn);
        return new ResponseEntity<>(modelMapper.map(violationOut,ViolationDTO.class),HttpStatus.ACCEPTED);
    }
}
