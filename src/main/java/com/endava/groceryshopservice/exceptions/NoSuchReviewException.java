package com.endava.groceryshopservice.exceptions;

public class NoSuchReviewException extends IllegalArgumentException {
    public NoSuchReviewException(String message) {
        super(message);
    }
}
