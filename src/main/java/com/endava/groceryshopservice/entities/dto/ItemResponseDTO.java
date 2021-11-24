package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import com.endava.groceryshopservice.entities.types.SizeTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Api(value = "Model for cart items in registration and authentication response")
@Data
@Builder
@AllArgsConstructor
public class ItemResponseDTO {

    @ApiModelProperty(value = "Product's unique id", example = "1")
    private Long productId;

    @ApiModelProperty(value = "Item's quantity", example = "1")
    private Integer quantity;

    @ApiModelProperty(value = "Url to an image", example = "https://www.mantralabsglobal.com/404")
    private String image;

    @ApiModelProperty(value = "Product's name", example = "Eggs")
    private String name;

    @ApiModelProperty(value = "Product's price", example = "200.00")
    private Double price;

    @ApiModelProperty(value = "Product type", notes = "could be either PACKS or KILOS", example = "PACKS")
    private SizeTypes sizeType;

    @ApiModelProperty(value = "Item's size", example = "5")
    private Integer size;
}
