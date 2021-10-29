package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.exceptions.NotSuitablePasswordException;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class PasswordValidatorService implements Predicate<String> {

    @Override
    public boolean test(String s) {
        if (!s.matches("\\A\\$2(a|y|b)?\\$12\\$[./0-9A-Za-z]{53}")) {
            throw new NotSuitablePasswordException("Password is not valids.");
        }
        return true;
    }
}
