package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Cart;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartUtils {
    public static final Cart CART = Cart.builder()
            .id(ID_ONE)
            .user(USER_ONE)
            .build();
}