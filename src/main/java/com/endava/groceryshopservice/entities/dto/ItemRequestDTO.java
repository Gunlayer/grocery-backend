package com.endava.groceryshopservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemRequestDTO {
    private Integer quantity;
    private Integer size;
    private Long productId;
}
