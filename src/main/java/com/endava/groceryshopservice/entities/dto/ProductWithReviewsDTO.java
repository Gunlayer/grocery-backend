package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.Review;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Product with list of it's reviews model information.")
public class ProductWithReviewsDTO extends ProductWithDescDTO {

    @ApiModelProperty(value = "List of product reviews", example = "Review #1, Review #2")
    private List<ReviewForProductDTO> reviews;

    public ProductWithReviewsDTO(Product product, List<Review> reviews) {
        super(product);
        this.reviews = reviews.stream()
                .map(ReviewForProductDTO::new)
                .collect(Collectors.toList());
    }

    public List<ReviewForProductDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews.stream()
                .map(ReviewForProductDTO::new)
                .collect(Collectors.toList());
    }
}