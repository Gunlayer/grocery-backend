package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Views;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestConstants {
    public static final Long ID_ONE = 1L;
    public static final Long ID_TWO = 2L;
    public static final Long ID_THREE = 3L;

    public static final String USER_EMAIL = "test@gmail.com";
    public static final String PASSWORD = "testPassword";
    public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQGVuZGF2YS5jb20iLCJyb2xlIjoiVVNFUiIsImlhdCI6MTYzNjk2NTc2MCwiZXhwIjoxNjM3NTcwNTYwfQ.zAYBUuEKyPZoBhJgnHtDUfe61kk6E2DIZYHVlVdaor0";

    public static final String IMAGE = "https://testImageUrl/";
    public static final String NAME = "Apples";
    public static final Double PRICE = 20.0;
    public static final Double RATING = 5.0;
    public static final String DESCRIPTION = "description";
    public static final Integer SIZE = 2;

    public static final List<Integer> SIZES = Arrays.asList(5, 7, 10);

}
