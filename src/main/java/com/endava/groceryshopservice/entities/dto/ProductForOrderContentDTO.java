package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Api(value = "Product model information")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForOrderContentDTO {

    @ApiModelProperty(value = "Product id")
    private Long productId;

    @ApiModelProperty(value = "Product quantity")
    private Integer quantity;

    @ApiModelProperty(value = "Quantity size")
    private Integer size;
}
