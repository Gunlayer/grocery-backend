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

    public static final CheckoutRequestDTO CHECKOUT_REQUEST = CheckoutRequestDTO.builder()
            .needToSave(true).email(USER_EMAIL).firstName(FIRST_NAME).lastName(LAST_NAME)
            .address(ADDRESS).apartment(APARTMENT).build();
}
