package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.types.SizeTypes;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.endava.groceryshopservice.utils.TestConstants.APPLES;
import static com.endava.groceryshopservice.utils.TestConstants.DESCRIPTION;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_THREE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.IMAGE;
import static com.endava.groceryshopservice.utils.TestConstants.PLUMS;
import static com.endava.groceryshopservice.utils.TestConstants.NAME;
import static com.endava.groceryshopservice.utils.TestConstants.NAME_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.PRICE;
import static com.endava.groceryshopservice.utils.TestConstants.RATING;
import static com.endava.groceryshopservice.utils.TestConstants.SIZES;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductUtils {

    public static final Product PRODUCT_ONE = Product.builder()
            .id(ID_ONE)
            .image(IMAGE)
            .name(APPLES)
            .price(PRICE)
            .rating(RATING)
            .description(DESCRIPTION)
            .sizes(SIZES)
            .sizeType(SizeTypes.PACKS)
            .build();

    public static final Product PRODUCT_TWO = Product.builder()
            .id(ID_TWO)
            .image(IMAGE)
            .name(PLUMS)
            .price(PRICE)
            .rating(RATING)
            .description(DESCRIPTION)
            .sizes(SIZES)
            .sizeType(SizeTypes.KILOS)
            .build();

    public static final Product PRODUCT_THREE = Product.builder()
            .id(ID_TWO)
            .image(IMAGE)
            .name(NAME)
            .price(PRICE)
            .rating(RATING)
            .description(DESCRIPTION)
            .sizes(SIZES)
            .sizeType(SizeTypes.KILOS)
            .build();

    public static final Product PRODUCT_FOUR = Product.builder()
            .id(ID_TWO)
            .image(IMAGE)
            .name(NAME)
            .price(PRICE)
            .rating(RATING)
            .description(DESCRIPTION)
            .sizes(SIZES)
            .sizeType(SizeTypes.KILOS)
            .build();

    public static final Product PRODUCT_FIVE = Product.builder()
            .id(ID_TWO)
            .image(IMAGE)
            .name(NAME)
            .price(PRICE)
            .rating(RATING)
            .description(DESCRIPTION)
            .sizes(SIZES)
            .sizeType(SizeTypes.KILOS)
            .build();

    public static final Product PRODUCT_THREE = Product.builder()
            .id(ID_THREE)
            .image(IMAGE)
            .name(NAME_TWO)
            .price(PRICE)
            .rating(RATING)
            .description(DESCRIPTION)
            .sizes(SIZES)
            .sizeType(SizeTypes.KILOS)
            .build();
}