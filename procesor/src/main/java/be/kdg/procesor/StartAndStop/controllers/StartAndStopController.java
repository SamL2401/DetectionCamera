package be.kdg.procesor.StartAndStop.controllers;

import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/startStop")
public class StartAndStopController {
    private final RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

    public StartAndStopController(RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry) {
        this.rabbitListenerEndpointRegistry = rabbitListenerEndpointRegistry;
        this.rabbitListenerEndpointRegistry.getListenerContainers();
    }

    @GetMapping("/start")
    public ModelAndView startQueue() {
        rabbitListenerEndpointRegistry.getListenerContainers();
        for (MessageListenerContainer listenerContainer : rabbitListenerEndpointRegistry.getListenerContainers()) {
            listenerContainer.start();
        }
        return new ModelAndView("index");
    }

    @GetMapping("/stop")
    public ModelAndView stopQueue() {
        rabbitListenerEndpointRegistry.getListenerContainers();
        for (MessageListenerContainer listenerContainer : rabbitListenerEndpointRegistry.getListenerContainers()) {
            listenerContainer.stop();
        }
        return new ModelAndView("index");
    }
}
