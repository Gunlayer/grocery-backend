package com.endava.groceryshopservice.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence plainTextPassword) {
        return plainTextPassword.toString();
    }

    @Override
    public boolean matches(CharSequence plainTextPassword, String passwordInDatabase) {
        return plainTextPassword.toString().equals(passwordInDatabase);
    }
}