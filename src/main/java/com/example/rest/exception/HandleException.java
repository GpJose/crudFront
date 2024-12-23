package com.example.rest.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(value = "com.example.rest.controller.ApiController")
@Log4j2
public class HandleException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployersNotFoundException.class)
    public ResponseEntity<HttpStatus> handleEmployersNotFoundException(EmployersNotFoundException ex) {

        log.warn(ex.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpStatus> handleValidationExceptions(Exception ex) {
        log.error(ex.getMessage());
        ex.printStackTrace();

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
