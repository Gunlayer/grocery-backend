package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.OrderContent;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import static com.endava.groceryshopservice.utils.OrderUtils.ORDER_ONE;
import static com.endava.groceryshopservice.utils.OrderUtils.ORDER_TWO;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_THREE;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.ID_FIVE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_FOUR;
import static com.endava.groceryshopservice.utils.TestConstants.QUANTITY_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.SIZE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderContentUtil {

    public static final OrderContent ORDER_CONTENT = OrderContent.builder()
            .id(ID_FIVE)
            .order(ORDER_ONE)
            .product(PRODUCT_TWO)
            .quantity(QUANTITY_TWO)
            .size(SIZE)
            .build();

    public static final OrderContent ORDER_CONTENT_ONE = OrderContent.builder()
            .id(ID_FOUR)
            .order(ORDER_TWO)
            .product(PRODUCT_TWO)
            .quantity(QUANTITY_TWO)
            .size(SIZE)
            .build();

    public static final OrderContent ORDER_CONTENT_TWO = OrderContent.builder()
            .id(ID_FOUR)
            .order(ORDER_TWO)
            .product(PRODUCT_THREE)
            .quantity(QUANTITY_TWO)
            .size(SIZE)
            .build();

    public static final List<OrderContent> ORDER_CONTENT_LIST = List.of(ORDER_CONTENT_ONE, ORDER_CONTENT_TWO);
}