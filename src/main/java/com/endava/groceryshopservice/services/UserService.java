package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);

    User getByEmail(String email);
}