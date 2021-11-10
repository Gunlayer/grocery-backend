package com.endava.groceryshopservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestConstants {
    public static final Long ID_ONE = 1L;
    public static final Long ID_TWO = 2L;
    public static final String IMAGE = "https://testImageUrl/";
    public static final String NAME = "Apples";
    public static final Double PRICE = 20.0;
    public static final Double RATING = 5.0;
    public static final List<Integer> SIZES = Arrays.asList(5, 7, 10);
}