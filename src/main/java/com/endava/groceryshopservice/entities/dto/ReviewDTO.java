package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import com.endava.groceryshopservice.entities.Review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Api(value = "Review with user email and product details model information")
@NoArgsConstructor
public class ReviewDTO extends ReviewForProductDTO {

    @Setter
    @Getter
    @ApiModelProperty(value = "Product details without description", example = "Apples")
    private ProductNoDescDTO product;

    public ReviewDTO(Review review) {
        super(review);
        this.product = new ProductNoDescDTO(review.getProduct());
    }
}