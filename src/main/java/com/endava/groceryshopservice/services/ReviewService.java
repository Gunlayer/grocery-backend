package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviewsByProductId(long id);

    List<Review> getAllReviews();

    Review getById(long id);

    void delete(long id);

    Review addReview(long productId, String userEmail, Review review);
}
