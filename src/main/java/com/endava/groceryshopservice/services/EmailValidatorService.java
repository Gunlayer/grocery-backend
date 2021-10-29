package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.exceptions.InvalidEmailException;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidatorService implements Predicate<String> {
    @Override
    public boolean test(String s) {
        if (s.isEmpty()) {
            throw new InvalidEmailException("Email cannot be null.");
        }
        if (s.matches("^(.+)@(.+\\.)+(.+)$")) {
            return true;
        }
        throw new InvalidEmailException("Email is not valid.");
    }
}