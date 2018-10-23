package be.kdg.procesor.violations.controllers.web;

import be.kdg.procesor.violations.dto.ViolationDTO;
import be.kdg.procesor.violations.exceptions.ViolationException;
import be.kdg.procesor.violations.model.Violation;
import be.kdg.procesor.violations.services.ViolationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * This class is responsible for handling the web calls from violations
 *
 * @author Sam Laureys
 * @version 1.01
 */
@Controller
@RequestMapping("/violation")
public class ViolationWebController {
    private final ViolationService violationService;
    private final ModelMapper modelMapper;

    public ViolationWebController(ViolationService violationService, ModelMapper modelMapper) {
        this.violationService = violationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/violation.do")
    public ModelAndView showViolationForm(ViolationDTO violationDTO) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("violationForm");
        mv.addObject("violationDTO", violationDTO);
        return mv;
    }

    @GetMapping("/allViolation.show")
    public ModelAndView showAllViolations() throws ViolationException {
        List<Violation> violations = violationService.getViolationsList();
        return new ModelAndView("violationSum", "violationDTOs", modelMapper.map(violations, ViolationDTO.class));
    }

    @PostMapping("/newViolation.do")
    public ModelAndView createViolation(@Valid @ModelAttribute ViolationDTO violationDTO) {
        Violation savedViolation = violationService.save(modelMapper.map(violationDTO, Violation.class));
        return new ModelAndView(
                //redirecten anders kan men die herroepen via "redirect:/greetingsum",
                "violationSum",
                "violationDTO",
                modelMapper.map(savedViolation,
                        ViolationDTO.class));
    }
}
