package com.endava.groceryshopservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.endava.groceryshopservice.entities.dto.ReviewDTO;
import com.endava.groceryshopservice.entities.dto.ReviewForProductDTO;
import com.endava.groceryshopservice.services.ReviewService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Review controller adds functionality to manipulate reviews")
@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @ApiOperation(value = "fetches all existing reviews")
    @GetMapping
    List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews().stream()
                .map(ReviewDTO::new)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "fetches all existing reviews for certain product (by product id)")
    @GetMapping("/{id}")
    List<ReviewForProductDTO> getAllReviewsByProductId(@PathVariable long id) {
        return reviewService.getAllReviewsByProductId(id).stream()
                .map(ReviewForProductDTO::new)
                .collect(Collectors.toList());
    }
}