package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Address;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.endava.groceryshopservice.utils.TestConstants.ADDRESS;
import static com.endava.groceryshopservice.utils.TestConstants.APARTMENT;
import static com.endava.groceryshopservice.utils.TestConstants.FIRST_NAME;
import static com.endava.groceryshopservice.utils.TestConstants.LAST_NAME;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressUtils {

    public static final Address ADDRESS_ONE = new Address(USER_EMAIL, USER_ONE, FIRST_NAME, LAST_NAME, ADDRESS, APARTMENT);
}
