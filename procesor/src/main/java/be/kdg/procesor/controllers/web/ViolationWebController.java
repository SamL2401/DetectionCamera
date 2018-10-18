package be.kdg.procesor.controllers.web;

import be.kdg.procesor.dto.ViolationDTO;
import be.kdg.procesor.model.violations.Violation;
import be.kdg.procesor.services.ViolationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
        return new ModelAndView("violationForm","violationDTO",violationDTO);
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
