package com.endava.groceryshopservice.services;

import java.util.function.Predicate;

public interface EmailValidationService  extends Predicate<String> {
    @Override
    boolean test(String s);
}
