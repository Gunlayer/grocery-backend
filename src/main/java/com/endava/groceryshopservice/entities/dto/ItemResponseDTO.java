package com.endava.groceryshopservice.entities.dto;

import com.endava.groceryshopservice.entities.types.SizeTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ItemResponseDTO {
    private Long productId;
    private Integer quantity;
    private String image;
    private String name;
    private Double price;
    private SizeTypes sizeType;
    private Integer size;
}
