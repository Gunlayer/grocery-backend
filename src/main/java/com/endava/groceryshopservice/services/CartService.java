package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.User;

public interface CartService {
    void createCartForUser(User user);
}