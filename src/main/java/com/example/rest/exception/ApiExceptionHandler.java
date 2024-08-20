package com.example.rest.exception;

import com.example.rest.controller.ApiController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ApiController.class)
@Log4j2
public class ApiExceptionHandler {

    @ExceptionHandler(EmployersNotFoundException.class)
    public ResponseEntity<Void> handleEmployersNotFoundException(EmployersNotFoundException e) {
        log.info(e.getMessage());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
