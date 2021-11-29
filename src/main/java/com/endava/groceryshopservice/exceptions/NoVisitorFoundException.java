package com.endava.groceryshopservice.exceptions;

public class NoVisitorFoundException extends RuntimeException {
    public NoVisitorFoundException(String message) {
        super(message);
    }
}