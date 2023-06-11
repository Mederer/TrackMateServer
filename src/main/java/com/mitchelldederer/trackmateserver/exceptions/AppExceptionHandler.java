package com.mitchelldederer.trackmateserver.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<AppExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(new AppExceptionResponse("A data integrity violation has occurred."), HttpStatus.BAD_REQUEST);
    }
}
