package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.endava.groceryshopservice.utils.CheckoutRequestUtils.CHECKOUT_REQUEST_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.CURRENT_DATE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_FIVE;
import static com.endava.groceryshopservice.utils.TestConstants.ORDER_EMAIL;
import static com.endava.groceryshopservice.utils.TestConstants.PRICE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderUtils {

    private static final String FULL_ADDRESS = CHECKOUT_REQUEST_TWO.getFirstName() + " " + CHECKOUT_REQUEST_TWO.getLastName() + ", " +
            CHECKOUT_REQUEST_TWO.getAddress() + " " + CHECKOUT_REQUEST_TWO.getApartment();
    public static final Order ORDER_ONE = Order.builder()
            .id(ID_FIVE)
            .email(ORDER_EMAIL)
            .address(FULL_ADDRESS)
            .price(PRICE)
            .orderDate(CURRENT_DATE)
            .build();
}
