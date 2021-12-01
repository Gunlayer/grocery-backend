package com.endava.groceryshopservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestConstants {
    public static final Long ID_ONE = 1L;
    public static final Long ID_TWO = 2L;
    public static final Long ID_THREE = 3L;
    public static final Long ID_FOUR = 4L;
    public static final Long ID_FIVE = 5L;
    public static final Long ID_SIX = 6L;

    public static final String USER_EMAIL = "user@endava.com";
    public static final String ADMIN_EMAIL = "admin@endava.com";
    public static final String ORDER_EMAIL = "order.email@endava.com";
    public static final String USER_PASSWORD = "user";
    public static final String ADMIN_PASSWORD = "admin";
    public static final String USER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.1.3";
    public static final String ADMIN_TOKEN = "eyJhbGciOiJIUzI1NiJ9.2.4";
    public static final String FIRST_NAME = "Jorik";
    public static final String LAST_NAME = "Barba";
    public static final String ADDRESS = "MD, Chisinau, Dacia 5";
    public static final String APARTMENT = "21";

    public static final String IMAGE = "https://testImageUrl/";
    public static final String NAME = "Apples";
    public static final String NAME_TWO = "Avocado";
    public static final Double PRICE = 20.0;
    public static final Double RATING = 5.0;
    public static final String DESCRIPTION = "description";
    public static final Integer SIZE = 2;
    public static final Integer QUANTITY = 1;
    public static final Integer QUANTITY_TWO = 2;
    public static final boolean IS_TRUE = true;

    public static final List<Integer> SIZES = Arrays.asList(5, 7, 10);

    public static final String PRODUCT_NOT_FOUND_EXCEPTION = "Could not find a product with id " + ID_ONE;
    public static final String ITEM_NOT_FOUND_EXCEPTION = "Could not find item ";
    public static final String QUANTITY_EXCEPTION = "Requested quantity cannot be bigger than stored quantity";
    public static final LocalDate CURRENT_DATE = LocalDate.now();

    public static final String VISITOR_ID = "865a384269d7602015c8eeddaf2fe329";
}