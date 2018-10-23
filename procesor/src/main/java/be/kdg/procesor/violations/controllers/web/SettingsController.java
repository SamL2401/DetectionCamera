package be.kdg.procesor.violations.controllers.web;

import be.kdg.procesor.violations.configs.FineCalculatorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * This class is responsible for handling the Web calls from settings
 *
 * @author Sam Laureys
 * @version 1.01
 */
@Controller
@RequestMapping("/settings")
public class SettingsController {
    @Autowired
    private FineCalculatorConfiguration fineCalculatorConfiguration;

    @GetMapping("/settings.show")
    public ModelAndView showViolationForm() {
        return new ModelAndView("settingsShow", "appProp", fineCalculatorConfiguration);
    }
}
