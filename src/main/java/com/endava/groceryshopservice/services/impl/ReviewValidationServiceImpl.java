package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Review;
import com.endava.groceryshopservice.exceptions.InvalidReviewException;
import com.endava.groceryshopservice.services.ReviewValidationService;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ReviewValidationServiceImpl implements ReviewValidationService {

    @Override
    public void validateReview(Review review) {
        if (isNull(review)) {
            throw new InvalidReviewException("Review cannot be null!");
        }
        checkCommentBody(review.getCommentBody());
        checkRating(review.getRating());
    }

    private void checkCommentBody(String text) {
        if (text == null) {
            throw new InvalidReviewException("Comment cannot be null.");
        }
        if (text.isEmpty()) {
            throw new InvalidReviewException("Comment cannot be empty.");
        }
        if (text.isBlank()) {
            throw new InvalidReviewException("Comment cannot be blank.");
        }
    }

    private void checkRating(int rating) {
        if (rating < 1) {
            throw new InvalidReviewException("Rating must not be less than 1.");
        }
        if (rating > 5) {
            throw new InvalidReviewException("Rating must be less than 5.");
        }
    }
}