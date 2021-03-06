package be.kdg.procesor.violations.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class handles a web ViolationException
 *
 * @author Sam Laureys
 * @version 1.01
 */
@ControllerAdvice
public class ViolationExceptionWebHandler {
    @ExceptionHandler(value = {ViolationException.class})
    protected ModelAndView handleViolationException() {
        return new ModelAndView("filesNotFound", HttpStatus.NOT_FOUND);
    }
}
