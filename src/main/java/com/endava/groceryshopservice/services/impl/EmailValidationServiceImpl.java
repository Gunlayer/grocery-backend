package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.exceptions.InvalidEmailException;
import com.endava.groceryshopservice.services.EmailValidationService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmailValidationServiceImpl implements EmailValidationService {
    @Override
    public boolean test(String s) {
        if (Objects.nonNull(s) && s.matches("^[a-zA-Z](\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$")) {
            return true;
        }
        throw new InvalidEmailException("Email is not valid.");
    }
}