package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.exceptions.NotSuitablePasswordException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Predicate;

@Service
public class PasswordValidationService implements Predicate<String> {

    @Override
    public boolean test(String s) {
        if (Objects.nonNull(s) && s.matches("\\A\\$2(a|y|b)?\\$12\\$[./0-9A-Za-z]{53}")) {
            return true;
        }
        throw new NotSuitablePasswordException("Password is not valid.");
    }
}
