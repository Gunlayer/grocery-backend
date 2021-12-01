package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.dto.MostSoldProductDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.List;

import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_FIVE;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_FOUR;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_THREE;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.QUANTITY;
import static com.endava.groceryshopservice.utils.TestConstants.QUANTITY_TWO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MostSoldProductUtils {
    public static final MostSoldProductDTO MOST_SOLD_PRODUCT_ONE = new MostSoldProductDTO(PRODUCT_ONE,QUANTITY_TWO);
    public static final MostSoldProductDTO MOST_SOLD_PRODUCT_TWO = new MostSoldProductDTO(PRODUCT_TWO,QUANTITY);
    public static final MostSoldProductDTO MOST_SOLD_PRODUCT_THREE = new MostSoldProductDTO(PRODUCT_THREE,QUANTITY);
    public static final MostSoldProductDTO MOST_SOLD_PRODUCT_FOUR = new MostSoldProductDTO(PRODUCT_FOUR,QUANTITY);
    public static final MostSoldProductDTO MOST_SOLD_PRODUCT_FIVE = new MostSoldProductDTO(PRODUCT_FIVE,QUANTITY);

    public static final List<MostSoldProductDTO> MOST_SOLD_PRODUCT_DTO_LIST = List.of(
            MOST_SOLD_PRODUCT_ONE,
            MOST_SOLD_PRODUCT_TWO,
            MOST_SOLD_PRODUCT_THREE,
            MOST_SOLD_PRODUCT_FOUR,
            MOST_SOLD_PRODUCT_FIVE);
}