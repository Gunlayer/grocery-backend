package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import com.endava.groceryshopservice.entities.Review;

import lombok.Data;
import lombok.NoArgsConstructor;

@Api(value = "Review model basic information")
@Data
@NoArgsConstructor
public class ReviewForProductDTO {

    @ApiModelProperty(value = "Review's id", example = "1")
    private long reviewId;

    @ApiModelProperty(value = "Review comment", example = "A very good product")
    private String commentBody;

    @ApiModelProperty(value = "Rating", example = "3", notes = "value between 1 and 5")
    private int rating;

    @ApiModelProperty(value = "User-author of the review", example = "user@endava.com",
            notes = "currently user name is represented by user email")
    private String userEmail;

    public ReviewForProductDTO(Review review) {
        this.reviewId = review.getReviewId();
        this.commentBody = review.getCommentBody();
        this.rating = review.getRating();
        this.userEmail = review.getUser().getEmail();
    }
}