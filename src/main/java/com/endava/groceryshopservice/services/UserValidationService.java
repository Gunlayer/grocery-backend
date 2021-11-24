package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.User;

public interface UserValidationService {
    void testUserValidation(User user);

    void testEmailValidation(String email);

    void testPasswordValidation(String password);
}