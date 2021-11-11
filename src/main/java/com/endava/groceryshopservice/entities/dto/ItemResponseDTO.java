package com.endava.groceryshopservice.entities.dto;

import com.endava.groceryshopservice.entities.types.SizeTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ItemResponseDTO {
    private Long id;
    private Long user_id;
    private Long product_id;
    private Integer quantity;
    private String image;
    private String name;
    private Double price;
    private Double rating;
    private String description;
    private SizeTypes sizeType;
    private Integer size;
}