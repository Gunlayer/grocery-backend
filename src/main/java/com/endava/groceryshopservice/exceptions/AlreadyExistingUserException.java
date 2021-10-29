package com.endava.groceryshopservice.exceptions;

public class AlreadyExistingUserException extends Exception {
    public AlreadyExistingUserException(String message) {
        super(message);
    }
}