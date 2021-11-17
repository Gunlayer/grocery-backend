package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.Views;
import com.endava.groceryshopservice.entities.types.SizeTypes;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.endava.groceryshopservice.utils.TestConstants.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductUtils {

    public static final Product PRODUCT_ONE = Product.builder()
            .id(ID_ONE)
            .image(IMAGE)
            .name(NAME)
            .price(PRICE)
            .rating(RATING)
            .description(DESCRIPTION)
            .sizes(SIZES)
            .sizeType(SizeTypes.PACKS)
            .build();

    public static final Product PRODUCT_TWO = Product.builder()
            .id(ID_TWO)
            .image(IMAGE)
            .name(NAME)
            .price(PRICE)
            .rating(RATING)
            .description(DESCRIPTION)
            .sizes(SIZES)
            .sizeType(SizeTypes.PACKS)
            .build();
}