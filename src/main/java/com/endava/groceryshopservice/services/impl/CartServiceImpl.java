package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Cart;
import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.repositories.CartRepository;
import com.endava.groceryshopservice.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public void createCartForUser(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
    }
}