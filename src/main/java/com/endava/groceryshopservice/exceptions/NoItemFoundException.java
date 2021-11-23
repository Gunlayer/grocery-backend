package com.endava.groceryshopservice.exceptions;

public class NoItemFoundException extends RuntimeException {
    public NoItemFoundException(String message) {
        super(message);
    }
}