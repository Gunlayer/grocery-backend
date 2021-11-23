package com.endava.groceryshopservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.Review;
import com.endava.groceryshopservice.entities.dto.ProductNoDescDTO;
import com.endava.groceryshopservice.entities.dto.ProductWithDescDTO;
import com.endava.groceryshopservice.entities.dto.ProductWithReviewsDTO;
import com.endava.groceryshopservice.entities.dto.ReviewDTO;
import com.endava.groceryshopservice.security.JwtTokenProvider;
import com.endava.groceryshopservice.services.ProductService;
import com.endava.groceryshopservice.services.ReviewService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Product controller offers REST APIs to work with products")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ReviewService reviewService;
    private final JwtTokenProvider tokenProvider;

    @ApiOperation(value = "fetches all the products")
    @GetMapping
    public Page<ProductNoDescDTO> getAllProducts(Pageable page) {
        List<ProductNoDescDTO> productNoDescDTOList = productService.getAll(page)
                .stream()
                .map(ProductNoDescDTO::new)
                .collect(Collectors.toList());

        return new PageImpl<>(productNoDescDTOList);
    }

    @ApiOperation(value = "gets a product by ID")
    @GetMapping("/{id}")
    public ProductWithReviewsDTO getProductById(@PathVariable long id) {
        Product product = productService.getById(id);
        List<Review> reviews = reviewService.getAllReviewsByProductId(id);
        return new ProductWithReviewsDTO(product, reviews);
    }

    @ApiOperation(value = "introduces new product")
    @PostMapping
    public ProductWithDescDTO addProduct(@RequestBody Product product) {
        return new ProductWithDescDTO(productService.save(product));
    }

    @ApiOperation(value = "fetches certain number (15 by default) of mostly viewed products")
    @GetMapping("/mostpopular")
    public List<ProductNoDescDTO> getMostPopular(@RequestParam(defaultValue = "15") int number) {
        return productService.getMostViewed(number)
                .stream()
                .map(ProductNoDescDTO::new)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "adds a review from an authorized user for a product with specific id")
    @PostMapping("/{id}/add_review")
    @PreAuthorize("hasAuthority('users:addReview')")
    public ResponseEntity<ReviewDTO> addReview(@PathVariable long id, @RequestBody Review review, @RequestHeader HashMap<String, String> headers) {
        String userEmail = tokenProvider.getUsername(headers.get("authorization"));
        ReviewDTO savedReview = new ReviewDTO(reviewService.addReview(id, userEmail, review));
        return new ResponseEntity<>(savedReview, HttpStatus.ACCEPTED);
    }
}