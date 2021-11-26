package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.exceptions.InvalidEmailException;
import com.endava.groceryshopservice.exceptions.NotSuitablePasswordException;
import com.endava.groceryshopservice.services.UserValidationService;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserValidationServiceImpl implements UserValidationService {

    @Override
    public void testUserValidation(User user) {
        testEmailValidation(user.getEmail());
        testPasswordValidation(user.getPassword());
    }

    @Override
    public void testEmailValidation(String email) {
        if (Objects.isNull(email) || !email.matches("^(?![.])[A-z0-9.]{5,35}@[A-z0-9.]{1,10}\\.[A-z0-9.]{1,11}$")) {
            throw new InvalidEmailException("Email is not valid.");
        }
    }

    @Override
    public void testPasswordValidation(String password) {
        if (Objects.isNull(password) || !password.matches("^[A-z0-9'~!@#$%^&*()_+\\-=?.,;:'\\/\\\"|\\{\\}<>\\[\\]]{5,10}$")) {
            throw new NotSuitablePasswordException("Password is not valid.");
        }
    }
}