package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Item;
import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.entities.types.SizeTypes;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.DESCRIPTION;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.IMAGE;
import static com.endava.groceryshopservice.utils.TestConstants.NAME;
import static com.endava.groceryshopservice.utils.TestConstants.PRICE;
import static com.endava.groceryshopservice.utils.TestConstants.RATING;
import static com.endava.groceryshopservice.utils.TestConstants.SIZE;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemUtils {
    public static final List<Item> ITEMS_LIST = List.of(
            Item.builder()
                    .id(ID_ONE)
                    .product(PRODUCT_ONE)
                    .size(SIZE)
                    .user(USER_ONE)
                    .quantity(2)
                    .build()
    );

    public static final List<ItemResponseDTO> ITEM_RESPONSE_DTO = List.of(ItemResponseDTO.builder()
            .id(ID_ONE)
            .user_id(ID_ONE)
            .product_id(ID_ONE)
            .quantity(2)
            .image(IMAGE)
            .name(NAME)
            .price(PRICE)
            .rating(RATING)
            .description(DESCRIPTION)
            .sizeType(SizeTypes.PACKS)
            .size(SIZE)
            .build());
}
