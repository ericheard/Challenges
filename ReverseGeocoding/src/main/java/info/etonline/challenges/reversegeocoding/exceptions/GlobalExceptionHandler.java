package info.etonline.challenges.reversegeocoding.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

/**
 * Created by erich on 10/29/2016.
 */

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IntegrationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(IntegrationException e) {
        return "Error occurred with Integration Service: " + e.getMessage();
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleException(MissingServletRequestParameterException e) {
        return "Error occured wih input Parameters: " + e.getMessage();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(Exception e) {
        return "General error occurred: " + e.getMessage();
    }
}
