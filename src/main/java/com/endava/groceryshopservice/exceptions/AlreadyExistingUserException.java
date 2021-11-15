package com.endava.groceryshopservice.exceptions;

public class AlreadyExistingUserException extends RuntimeException {
    public AlreadyExistingUserException(String message) {
        super(message);
    }
}