package com.endava.groceryshopservice.entities.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemToAddRequestDTO extends ItemRequestDTO{
    private String userEmail;

    @Builder
    public ItemToAddRequestDTO(Integer quantity, Integer size, Long productId, String userEmail) {
        super(quantity, size, productId);
        this.userEmail = userEmail;
    }
}