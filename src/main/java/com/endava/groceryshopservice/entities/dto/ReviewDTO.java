package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import com.endava.groceryshopservice.entities.Review;

import lombok.Data;
import lombok.NoArgsConstructor;

@Api(value = "Review with user email and product details model information")
@Data
@NoArgsConstructor
public class ReviewDTO extends ReviewForProductDTO {

    @ApiModelProperty(value = "Product details without description", example = "Apples")
    private ProductNoDescDTO product;

    public ReviewDTO(Review review) {
        super(review);
        this.product = new ProductNoDescDTO(review.getProduct());
    }
}