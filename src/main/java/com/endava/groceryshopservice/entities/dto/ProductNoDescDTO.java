package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.types.SizeTypes;

import lombok.Data;

import java.util.List;

@Api(value = "Product model (without product description) information")
@Data
public class ProductNoDescDTO {

    @ApiModelProperty(value = "Product's unique id", example = "1")
    private Long id;

    @ApiModelProperty(value = "Url to an image",
            example = "https://www.mantralabsglobal.com/404")
    private String image;

    @ApiModelProperty(value = "Product's name", example = "Eggs")
    private String name;

    @ApiModelProperty(value = "Product's price", example = "200.00")
    private Double price;

    @ApiModelProperty(value = "Product's rating", example = "4.5",
            notes = "cannot be set up manually, it is recalcuulated with each newly added review")
    private Double rating;

    @ApiModelProperty(value = "A list of available sizes for each product", example = "[1,3,5]")
    private List<Integer> sizes;

    @ApiModelProperty(value = "Product type", notes = "could be either PACKS or KILOS", example = "PACKS")
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