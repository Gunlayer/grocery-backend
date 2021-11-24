package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import lombok.Builder;
import lombok.Getter;

@Api(value = "Model for add and delete requests")
@Getter
public class ItemToAddDeleteRequestDTO extends ItemRequestDTO {

    @ApiModelProperty(value = "User's email", example = "user@gmail.com")
    private final String userEmail;

    @Builder
    public ItemToAddDeleteRequestDTO(Integer quantity, Integer size, Long productId, String userEmail) {
        super(quantity, size, productId);
        this.userEmail = userEmail;
    }
}