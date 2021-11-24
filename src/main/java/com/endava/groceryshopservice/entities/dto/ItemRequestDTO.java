package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Api(value = "Model for cart items in registration and authentication requests")
@Data
@AllArgsConstructor
public class ItemRequestDTO {

    @ApiModelProperty(value = "Item's quantity", example = "1")
    private Integer quantity;

    @ApiModelProperty(value = "Item's size", example = "5")
    private Integer size;

    @ApiModelProperty(value = "Product's unique id", example = "1")
    private Long productId;
}
