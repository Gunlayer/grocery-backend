package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.exceptions.InvalidEmailException;
import com.endava.groceryshopservice.exceptions.NotSuitablePasswordException;
import com.endava.groceryshopservice.services.RegistrationValidationService;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RegistrationValidationServiceImpl implements RegistrationValidationService {

    @Override
    public void testEmailValidation(String email) {
        if (Objects.isNull(email) || !email.matches("^(?![._-])[A-z0-9._-]{1,30}@[A-z._-]{2,10}.[A-z]{2,6}$")) {
            throw new InvalidEmailException("Email is not valid.");
        }
    }

    @Override
    public void testPasswordValidation(String password) {
        if (Objects.isNull(password) || !password.matches("^[a-zA-Z0-9]+$")) {
            throw new NotSuitablePasswordException("Password is not valid.");
        }
    }
}