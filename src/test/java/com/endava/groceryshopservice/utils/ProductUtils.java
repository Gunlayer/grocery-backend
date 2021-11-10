package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.dto.ProductNoDescDTO;
import com.endava.groceryshopservice.entities.types.SizeTypes;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.IMAGE;
import static com.endava.groceryshopservice.utils.TestConstants.NAME;
import static com.endava.groceryshopservice.utils.TestConstants.PRICE;
import static com.endava.groceryshopservice.utils.TestConstants.RATING;
import static com.endava.groceryshopservice.utils.TestConstants.SIZES;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductUtils {

    public static final ProductNoDescDTO PRODUCT_NO_DESC_DTO_1 = new ProductNoDescDTO(Product.builder()
            .id(ID_ONE)
            .image(IMAGE)
            .name(NAME)
            .price(PRICE)
            .rating(RATING)
            .sizes(SIZES)
            .sizeType(SizeTypes.PACKS)
            .build());

    public static final ProductNoDescDTO PRODUCT_NO_DESC_DTO_2 = new ProductNoDescDTO(Product.builder()
            .id(ID_TWO)
            .image(IMAGE)
            .name(NAME)
            .price(PRICE)
            .rating(RATING)
            .sizes(SIZES)
            .sizeType(SizeTypes.PACKS)
            .build());
}