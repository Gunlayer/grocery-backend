package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.dto.CheckoutRequestDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.endava.groceryshopservice.utils.TestConstants.ADDRESS;
import static com.endava.groceryshopservice.utils.TestConstants.APARTMENT;
import static com.endava.groceryshopservice.utils.TestConstants.FIRST_NAME;
import static com.endava.groceryshopservice.utils.TestConstants.LAST_NAME;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckoutRequest {

    public static final CheckoutRequestDTO CHECKOUT_REQUEST = new CheckoutRequestDTO(
            true, USER_EMAIL, FIRST_NAME, LAST_NAME, ADDRESS, APARTMENT);
}
