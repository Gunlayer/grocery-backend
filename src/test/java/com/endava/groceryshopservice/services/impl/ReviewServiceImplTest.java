package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Review;
import com.endava.groceryshopservice.repositories.ReviewRepository;
import com.endava.groceryshopservice.services.ProductService;
import com.endava.groceryshopservice.services.ReviewValidationService;
import com.endava.groceryshopservice.services.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.ReviewUtils.REVIEW;
import static com.endava.groceryshopservice.utils.ReviewUtils.REVIEW_LIST_PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.ReviewUtils.REVIEW_LIST_PRODUCT_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.ID_FOUR;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    ProductService productService;

    @Mock
    ReviewValidationService validationService;

    @Mock
    UserService userService;

    @InjectMocks
    ReviewServiceImpl reviewService;


    @Test
    void shouldGetAllReviewsByProductId() {
        when(reviewRepository.findAllByProductId(ID_ONE)).thenReturn(REVIEW_LIST_PRODUCT_ONE);

        assertThat(reviewService.getAllReviewsByProductId(ID_ONE)).isEqualTo(REVIEW_LIST_PRODUCT_ONE);
    }

    @Test
    void shouldGetAllReviews() {
        List<Review> expected = new ArrayList(REVIEW_LIST_PRODUCT_ONE);
        expected.addAll(REVIEW_LIST_PRODUCT_TWO);

        when(reviewRepository.findAll()).thenReturn(expected);

        assertThat(reviewService.getAllReviews()).isEqualTo(expected);
    }

    @Test
    void shouldGetAReviewById() {
        when(reviewRepository.findById(ID_ONE)).thenReturn(Optional.of(REVIEW));

        assertThat(reviewService.getById(ID_ONE)).isEqualTo(REVIEW);
    }

    @Test
    void shouldAddReview() {
        when(reviewRepository.save(REVIEW)).thenReturn(REVIEW);
        when(userService.getByEmail(USER_EMAIL)).thenReturn(USER_ONE);
        when(productService.getById(ID_ONE)).thenReturn(PRODUCT_ONE);

        assertThat(reviewService.addReview(ID_ONE, USER_EMAIL, REVIEW)).isEqualTo(REVIEW);
    }

    @Test
    void shouldDeleteReview() {
        when(reviewRepository.findById(ID_FOUR)).thenReturn(Optional.of(REVIEW));

        reviewService.delete(ID_FOUR);

        verify(reviewRepository, times(1)).delete(REVIEW);
    }
}