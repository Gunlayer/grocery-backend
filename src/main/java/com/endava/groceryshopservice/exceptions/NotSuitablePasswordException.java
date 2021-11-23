package com.endava.groceryshopservice.exceptions;

public class NotSuitablePasswordException extends IllegalStateException {
    public NotSuitablePasswordException(String message) {
        super(message);
    }
}