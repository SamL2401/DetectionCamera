package be.kdg.procesor.violations.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class handles a rest ViolationException
 *
 * @author Sam Laureys
 * @version 1.01
 */
@ControllerAdvice(annotations = RestController.class)
public class ViolationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ViolationException.class})
    protected ResponseEntity<?> handleViolationNotFound(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }
}
