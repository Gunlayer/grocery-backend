package com.endava.groceryshopservice.entities.dto;

import com.endava.groceryshopservice.entities.Product;

public class ProductWithDescDTO extends ProductNoDescDTO {
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