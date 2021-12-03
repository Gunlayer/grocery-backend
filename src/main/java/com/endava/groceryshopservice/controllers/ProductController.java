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
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<Page<ProductWithDescDTO>> getAllProducts(@RequestParam(name = "name", defaultValue = "") String name, Pageable pageable) {
        List<ProductWithDescDTO> productWithDescDTOList = productService.getAll(name, pageable)
                .stream()
                .map(ProductWithDescDTO::new)
                .collect(Collectors.toList());
        long totalProducts = productService.getCountAll(name);
        return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<>(productWithDescDTOList, pageable, totalProducts));
    }

    @ApiOperation(value = "gets a product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductWithReviewsDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getById(id);
        List<Review> reviews = reviewService.getAllReviewsByProductId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductWithReviewsDTO(product, reviews));
    }

    @ApiOperation(value = "introduces new product or edit the existing one")
    @PreAuthorize("hasAuthority('users:write')")
    @PostMapping
    public ResponseEntity<ProductWithDescDTO> saveProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductWithDescDTO(productService.save(product)));
    }

    @ApiOperation(value = "deletes a product by id")
    @PreAuthorize("hasAuthority('users:write')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductWithDescDTO> deleteProduct(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ProductWithDescDTO(productService.deleteById(id)));
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
    @PreAuthorize("hasAuthority('users:addReview')")
    @PostMapping("/{id}/add_review")
    public ResponseEntity<ReviewDTO> addReview(@PathVariable long id, @RequestBody Review review, @RequestHeader HashMap<String, String> headers) {
        String userEmail = tokenProvider.getUsername(headers.get("authorization"));
        ReviewDTO savedReview = new ReviewDTO(reviewService.addReview(id, userEmail, review));
        return new ResponseEntity<>(savedReview, HttpStatus.ACCEPTED);
    }
}