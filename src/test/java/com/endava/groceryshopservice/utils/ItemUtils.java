package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Item;
import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.entities.dto.ItemToAddRequestDTO;
import com.endava.groceryshopservice.entities.types.SizeTypes;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.IMAGE;
import static com.endava.groceryshopservice.utils.TestConstants.NAME;
import static com.endava.groceryshopservice.utils.TestConstants.PRICE;
import static com.endava.groceryshopservice.utils.TestConstants.QUANTITY;
import static com.endava.groceryshopservice.utils.TestConstants.SIZE;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemUtils {

    public static final Item ITEM_ONE = Item.builder()
            .product(PRODUCT_ONE)
            .size(SIZE)
            .user(USER_ONE)
            .quantity(QUANTITY)
            .build();

    public static final Item FINAL_ITEM = Item.builder()
            .id(ID_ONE)
            .product(PRODUCT_ONE)
            .size(SIZE)
            .user(USER_ONE)
            .quantity(QUANTITY + QUANTITY)
            .build();

    public static final List<Item> ITEMS_LIST = List.of(ITEM_ONE);

    public static final List<ItemResponseDTO> ITEM_RESPONSE_DTO = List.of(ItemResponseDTO.builder()
            .productId(ID_ONE)
            .quantity(QUANTITY)
            .image(IMAGE)
            .name(NAME)
            .price(PRICE)
            .sizeType(SizeTypes.PACKS)
            .size(SIZE)
            .build());

    public static final ItemToAddRequestDTO ITEM_TO_ADD_REQUEST_DTO = ItemToAddRequestDTO.builder()
            .userEmail(USER_EMAIL)
            .productId(PRODUCT_ONE.getId())
            .quantity(QUANTITY)
            .size(SIZE)
            .build();
}
