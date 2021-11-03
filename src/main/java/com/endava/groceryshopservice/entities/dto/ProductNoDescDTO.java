package com.endava.groceryshopservice.entities.dto;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.types.SizeTypes;

import lombok.Data;

import java.util.List;

@Data
public class ProductNoDescDTO {
    private Long id;
    private String image;
    private String name;
    private Double price;
    private Double rating;
    private List<Integer> sizes;
    private SizeTypes sizeType;

    public ProductNoDescDTO(Product product) {
        id = product.getId();
        image = product.getImage();
        name = product.getName();
        price = product.getPrice();
        rating = product.getRating();
        sizes = product.getSizes();
        sizeType = product.getSizeType();
    }
}