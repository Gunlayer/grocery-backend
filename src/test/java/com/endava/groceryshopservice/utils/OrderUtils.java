package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import static com.endava.groceryshopservice.utils.CheckoutRequestUtils.CHECKOUT_REQUEST_TWO;
import static com.endava.groceryshopservice.utils.OrderContentUtil.ORDER_CONTENT_LIST;
import static com.endava.groceryshopservice.utils.TestConstants.CURRENT_DATE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_FIVE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_THREE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.ORDER_EMAIL;
import static com.endava.groceryshopservice.utils.TestConstants.PRICE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderUtils {

    private static final String FULL_ADDRESS = CHECKOUT_REQUEST_TWO.getFirstName() + " " + CHECKOUT_REQUEST_TWO.getLastName() + ", " +
            CHECKOUT_REQUEST_TWO.getAddress() + " " + CHECKOUT_REQUEST_TWO.getApartment();
    public static final Order ORDER = Order.builder()
            .id(ID_FIVE)
            .email(ORDER_EMAIL)
            .address(FULL_ADDRESS)
            .price(PRICE)
            .orderDate(CURRENT_DATE)
            .build();

    public static final Order ORDER_ONE = Order.builder()
            .id(ID_FIVE)
            .email(ORDER_EMAIL)
            .address(FULL_ADDRESS)
            .price(PRICE)
            .orderDate(CURRENT_DATE)
            .orderContent(ORDER_CONTENT_LIST)
            .build();

    public static final Order ORDER_TWO = Order.builder()
            .id(ID_TWO)
            .email(ORDER_EMAIL)
            .address(FULL_ADDRESS)
            .price(PRICE)
            .orderDate(CURRENT_DATE)
            .orderContent(ORDER_CONTENT_LIST)
            .build();

    public static final Order ORDER_THREE = Order.builder()
            .id(ID_ONE)
            .email(ORDER_EMAIL)
            .address(FULL_ADDRESS)
            .price(PRICE)
            .orderDate(CURRENT_DATE)
            .orderContent(ORDER_CONTENT_LIST)
            .build();

    public static final Order ORDER_FOUR = Order.builder()
            .id(ID_TWO)
            .email(ORDER_EMAIL)
            .address(FULL_ADDRESS)
            .price(PRICE)
            .orderDate(CURRENT_DATE)
            .orderContent(ORDER_CONTENT_LIST)
            .build();

    public static final Order ORDER_FIVE = Order.builder()
            .id(ID_THREE)
            .email(ORDER_EMAIL)
            .address(FULL_ADDRESS)
            .price(PRICE)
            .orderDate(CURRENT_DATE)
            .orderContent(ORDER_CONTENT_LIST)
            .build();

    public static final List<Order> ORDER_LIST = List.of(ORDER_ONE,ORDER_TWO,ORDER_THREE, ORDER_FOUR, ORDER_FIVE);
}