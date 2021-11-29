package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.endava.groceryshopservice.utils.TestConstants.ADDRESS;
import static com.endava.groceryshopservice.utils.TestConstants.CURRENT_DATE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_FIVE;
import static com.endava.groceryshopservice.utils.TestConstants.ORDER_EMAIL;
import static com.endava.groceryshopservice.utils.TestConstants.PRICE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderUtils {

    public static final Order ORDER_ONE = Order.builder()
            .id(ID_FIVE)
            .email(ORDER_EMAIL)
            .address(ADDRESS)
            .price(PRICE)
            .orderDate(CURRENT_DATE)
            .build();
}
