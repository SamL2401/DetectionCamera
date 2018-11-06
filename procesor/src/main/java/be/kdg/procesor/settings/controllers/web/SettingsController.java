package be.kdg.procesor.settings.controllers.web;

import be.kdg.procesor.settings.configs.SettingsProcessorConfiguration;
import be.kdg.procesor.settings.dto.ProcessorSettingsDTO;
import be.kdg.procesor.settings.model.ProcessorSettings;
import be.kdg.procesor.settings.services.SettingsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * This class is responsible for handling the Web calls from settings
 *
 * @author Sam Laureys
 * @version 1.01
 */
@Controller
@RequestMapping("/settings")
public class SettingsController{
    private final SettingsService settingsService;
    private final ModelMapper modelMapper;

    public SettingsController(SettingsService settingsService, ModelMapper modelMapper) {
        this.settingsService = settingsService;
        this.modelMapper = modelMapper;
    }

    @Autowired
    private SettingsProcessorConfiguration settingsProcessorConfiguration;

    @GetMapping("/settings.show")
    public ModelAndView showSettings() {
        return new ModelAndView("settingsShow", "appProp", settingsProcessorConfiguration);
    }

    @GetMapping("/settings.do")
    public ModelAndView showSettingsForm(ProcessorSettingsDTO processorSettingsDTO) {
        return new ModelAndView("settingsForm","processorSettingsDTO",processorSettingsDTO);
    }

    @PostMapping("/updateSettings.do")
    public ModelAndView updateSettingsForm(@ModelAttribute ProcessorSettingsDTO processorSettingsDTO) {
        ProcessorSettings settings = settingsService.updateSettings(modelMapper.map(processorSettingsDTO, ProcessorSettings.class));
        return new ModelAndView("settingsForm", "processorSettingsDTO", modelMapper.map(settings,ProcessorSettingsDTO.class));
    }
}
