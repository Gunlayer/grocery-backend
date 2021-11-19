package com.endava.groceryshopservice.exceptions;

public class InvalidEmailException extends IllegalStateException {
    public InvalidEmailException(String message) {
        super(message);
    }
}