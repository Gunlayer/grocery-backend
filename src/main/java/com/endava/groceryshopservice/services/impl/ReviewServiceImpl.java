package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Review;
import com.endava.groceryshopservice.exceptions.NoSuchReviewException;
import com.endava.groceryshopservice.repositories.ReviewRepository;
import com.endava.groceryshopservice.services.ProductService;
import com.endava.groceryshopservice.services.ReviewService;
import com.endava.groceryshopservice.services.ReviewValidationService;
import com.endava.groceryshopservice.services.UserService;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final ReviewValidationService reviewValidationService;
    private final UserService userService;

    @Override
    public List<Review> getAllReviewsByProductId(long id) {
        return reviewRepository.findAllByProductId(id);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getById(long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchReviewException(String.format("Review with id %d does not exist.", id)));
    }

    @Override
    public Review addReview(long productId, String userEmail, Review review) {
        review.setProduct(productService.getById(productId));
        review.setUser(userService.getByEmail(userEmail));
        reviewValidationService.validateReview(review);
        Review savedReview = reviewRepository.save(review);
        productService.setRatingForProduct(productId);
        return savedReview;
    }

    @Override
    public void delete(long id) {
        Review review = getById(id);
        reviewRepository.delete(review);
    }
}
