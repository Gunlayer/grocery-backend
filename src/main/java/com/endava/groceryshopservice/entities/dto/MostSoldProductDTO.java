package com.endava.groceryshopservice.entities.dto;

import com.endava.groceryshopservice.entities.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api(value = "Most sold product model information")
@Data
public class MostSoldProductDTO {

    @ApiModelProperty(value = "Url to an image",
            example = "https://www.mantralabsglobal.com/404")
    private String image;

    @ApiModelProperty(value = "Product's name", example = "Eggs")
    private String name;

    @ApiModelProperty(value = "Product's quantity", example = "1")
    private Integer quantity;

    public MostSoldProductDTO(Product product, Integer quantity) {
        image = product.getImage();
        name = product.getName();
        this.quantity = quantity;
    }
}