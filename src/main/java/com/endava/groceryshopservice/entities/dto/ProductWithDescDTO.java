package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import com.endava.groceryshopservice.entities.Product;

@Api(value = "Product model info")
public class ProductWithDescDTO extends ProductNoDescDTO {

    @ApiModelProperty(value = "Description of the product",
            example = "This organic mamaliga is exceptional! Try it today, so you won't regret tomorrow!")
    private String description;

    public ProductWithDescDTO(Product product) {
        super(product);
        description = product.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}