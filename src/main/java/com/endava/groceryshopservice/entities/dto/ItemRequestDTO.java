package com.endava.groceryshopservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ItemRequestDTO {
    private Integer quantity;
    private Integer size;
    private Long productId;
}
