package com.endava.groceryshopservice.services;

import java.util.function.Predicate;

public interface PasswordValidationService extends Predicate<String> {

    @Override
    boolean test(String s);
}
