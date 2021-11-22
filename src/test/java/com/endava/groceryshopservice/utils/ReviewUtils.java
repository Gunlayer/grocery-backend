package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Review;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.ID_FOUR;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_THREE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_TWO;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewUtils {

    public static final List<Review> REVIEW_LIST_PRODUCT_ONE = List.of(
            new Review(ID_ONE, "Good product", 5, USER_ONE, PRODUCT_ONE),
            new Review(ID_TWO, "Bad product", 1, USER_ONE, PRODUCT_ONE)
    );

    public static final List<Review> REVIEW_LIST_PRODUCT_TWO = List.of(
            new Review(ID_THREE, "Good product", 4, USER_ONE, PRODUCT_TWO)
    );

    public static final Review REVIEW = new Review(ID_FOUR, "Bad product",
            2, USER_ONE, PRODUCT_ONE);
}
