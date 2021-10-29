package com.endava.groceryshopservice.exceptions;

public class InvalidEmailException extends IllegalStateException {
    public InvalidEmailException(String s) {
        super(s);
    }
}
