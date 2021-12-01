package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.dto.CheckoutRequestDTO;
import com.endava.groceryshopservice.entities.dto.ProductForOrderContentDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.endava.groceryshopservice.utils.TestConstants.ADDRESS;
import static com.endava.groceryshopservice.utils.TestConstants.APARTMENT;
import static com.endava.groceryshopservice.utils.TestConstants.FIRST_NAME;
import static com.endava.groceryshopservice.utils.TestConstants.ID_FIVE;
import static com.endava.groceryshopservice.utils.TestConstants.IS_TRUE;
import static com.endava.groceryshopservice.utils.TestConstants.LAST_NAME;
import static com.endava.groceryshopservice.utils.TestConstants.ORDER_EMAIL;
import static com.endava.groceryshopservice.utils.TestConstants.PRICE;
import static com.endava.groceryshopservice.utils.TestConstants.QUANTITY_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.SIZE;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckoutRequestUtils {

    public static final CheckoutRequestDTO CHECKOUT_REQUEST = CheckoutRequestDTO.builder()
            .needToSave(true).email(USER_EMAIL).firstName(FIRST_NAME).lastName(LAST_NAME)
            .address(ADDRESS).apartment(APARTMENT).build();

    public static final CheckoutRequestDTO CHECKOUT_REQUEST_TWO = CheckoutRequestDTO.builder()
            .needToSave(IS_TRUE)
            .totalPrice(PRICE)
            .email(ORDER_EMAIL)
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .address(ADDRESS)
            .apartment(APARTMENT)
            .orderList(List.of(new ProductForOrderContentDTO(ID_FIVE, QUANTITY_TWO, SIZE)))
            .build();
}
