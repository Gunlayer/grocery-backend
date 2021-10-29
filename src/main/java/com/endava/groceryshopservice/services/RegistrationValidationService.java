package com.endava.groceryshopservice.services;

public interface RegistrationValidationService {
    void testEmailValidation(String email);

    void testPasswordValidation(String password);
}