package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.exceptions.NoProductFoundException;
import com.endava.groceryshopservice.exceptions.model.ErrorData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerErrorHandler {
    @ExceptionHandler(NoProductFoundException.class)
    public ResponseEntity<ErrorData> noDataFoundExceptions(NoProductFoundException exception) {
        return getErrorResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorData> IllegalArgumentExceptions(IllegalArgumentException exception) {
        return getErrorResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    private ResponseEntity<ErrorData> getErrorResponseEntity(HttpStatus code, String message) {
        return new ResponseEntity<>(ErrorData.builder()
                .errorMessage(message)
                .status(code)
                .timeStamp(LocalDateTime.now())
                .build(),
                code);
    }
}