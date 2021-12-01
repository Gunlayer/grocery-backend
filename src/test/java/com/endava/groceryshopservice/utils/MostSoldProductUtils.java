package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.dto.MostSoldProductDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_THREE;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_TWO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MostSoldProductUtils {

    public static final List<MostSoldProductDTO> MOST_SOLD_PRODUCT_DTO_LIST = List.of(
            new MostSoldProductDTO(PRODUCT_TWO, 20),
            new MostSoldProductDTO(PRODUCT_THREE, 20));
}