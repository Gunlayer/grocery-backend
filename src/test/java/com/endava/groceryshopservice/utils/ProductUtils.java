package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.types.SizeTypes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.Arrays;
import java.util.List;
import static com.endava.groceryshopservice.utils.TestConstants.DESCRIPTION;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.IMAGE;
import static com.endava.groceryshopservice.utils.TestConstants.PRICE;
import static com.endava.groceryshopservice.utils.TestConstants.RATING;
import static com.endava.groceryshopservice.utils.TestConstants.NAME;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductUtils {
    public static final List<Integer> SIZE_LIST = Arrays.asList(1, 2);

    public static final Product PRODUCT = Product.builder()
            .id(ID_ONE)
            .image(IMAGE)
            .name(NAME)
            .price(PRICE)
            .rating(RATING)
            .description(DESCRIPTION)
            .sizes(SIZE_LIST)
            .sizeType(SizeTypes.PACKS)
            .build();
}
