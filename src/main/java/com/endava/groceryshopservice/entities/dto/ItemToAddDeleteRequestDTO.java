package com.endava.groceryshopservice.entities.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemToAddDeleteRequestDTO extends ItemRequestDTO {
    private String userEmail;

    @Builder
    public ItemToAddDeleteRequestDTO(Integer quantity, Integer size, Long productId, String userEmail) {
        super(quantity, size, productId);
        this.userEmail = userEmail;
    }
}