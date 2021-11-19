package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Review;
import com.endava.groceryshopservice.exceptions.InvalidReviewException;

import org.junit.jupiter.api.Test;

import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReviewValidationServiceImplTest {

    ReviewValidationServiceImpl validationService = new ReviewValidationServiceImpl();

    @Test
    void shouldNotValidateNullReview() {
        assertThrows(InvalidReviewException.class, () -> {
            validationService.validateReview(null);
        });
    }

    @Test
    void shouldNotValidateReviewWithNullComment() {
        Review review = new Review(ID_ONE, null, 4, USER_ONE, PRODUCT_ONE);

        assertThrows(InvalidReviewException.class, () -> {
            validationService.validateReview(review);
        });
    }

    @Test
    void shouldNotValidateReviewWithEmptyComment() {
        Review review = new Review(ID_ONE, "", 4, USER_ONE, PRODUCT_ONE);

        assertThrows(InvalidReviewException.class, () -> {
            validationService.validateReview(review);
        });
    }

    @Test
    void shouldNotValidateReviewWithBlankComment() {
        Review review = new Review(ID_ONE, "   ", 4, USER_ONE, PRODUCT_ONE);

        assertThrows(InvalidReviewException.class, () -> {
            validationService.validateReview(review);
        });
    }

    @Test
    void shouldNotValidateReviewNegativeRating() {
        Review review = new Review(ID_ONE, "Good product", -8, USER_ONE, PRODUCT_ONE);

        assertThrows(InvalidReviewException.class, () -> {
            validationService.validateReview(review);
        });
    }

    @Test
    void shouldNotValidateReviewWithRatingOverFive() {
        Review review = new Review(ID_ONE, "Good product", 6, USER_ONE, PRODUCT_ONE);

        assertThrows(InvalidReviewException.class, () -> {
            validationService.validateReview(review);
        });
    }

    @Test
    void shouldNotValidateReviewWithRatingZero() {
        Review review = new Review(ID_ONE, "Good product", 0, USER_ONE, PRODUCT_ONE);

        assertThrows(InvalidReviewException.class, () -> {
            validationService.validateReview(review);
        });
    }
}