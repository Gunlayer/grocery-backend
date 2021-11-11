package com.endava.groceryshopservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestConstants {
    public static final Long ID_ONE = 1L;
    public static final Long ID_TWO = 2L;

    public static final String USER_EMAIL = "test@gmail.com";
    public static final String PASSWORD = "$2a$12$WQmh2G/A6nsrnO2pTFd71eAlhWlI9uKNsSLkY5mTgONXxdd5GLbf.";

    public static final String IMAGE = "https://testImageUrl/";
    public static final String NAME = "Apples";
    public static final Double PRICE = 20.0;
    public static final Double RATING = 5.0;
    public static final String DESCRIPTION = "description";
    public static final Integer SIZE = 2;

    public static final List<Integer> SIZES = Arrays.asList(5, 7, 10);
}
