package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.exceptions.AlreadyExistingUserException;
import com.endava.groceryshopservice.exceptions.BadCredentialsException;
import com.endava.groceryshopservice.exceptions.InvalidEmailException;
import com.endava.groceryshopservice.exceptions.InvalidQuantityException;
import com.endava.groceryshopservice.exceptions.NoItemFoundException;
import com.endava.groceryshopservice.exceptions.NoProductFoundException;
import com.endava.groceryshopservice.exceptions.NotSuitablePasswordException;
import com.endava.groceryshopservice.exceptions.model.ErrorData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerErrorHandler {
    @ExceptionHandler({
            AlreadyExistingUserException.class,
            IllegalArgumentException.class,
            InvalidEmailException.class,
            NoProductFoundException.class,
            NotSuitablePasswordException.class,
            NoItemFoundException.class,
            InvalidQuantityException.class
    })
    public ResponseEntity<ErrorData> exceptionHandle(Exception exception) {
        return getErrorResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ErrorData> AuthenticationExceptionHandler() {
        SecurityContextHolder.clearContext();
        return getErrorResponseEntity(HttpStatus.UNAUTHORIZED, "Jwt is invalid");
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<ErrorData> BadCredentialsException(Exception exception) {
        return getErrorResponseEntity(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    private ResponseEntity<ErrorData> getErrorResponseEntity(HttpStatus code, String message) {
        return new ResponseEntity<>(ErrorData.builder()
                .message(message)
                .status(code)
                .timeStamp(LocalDateTime.now())
                .build(),
                code);
    }
}