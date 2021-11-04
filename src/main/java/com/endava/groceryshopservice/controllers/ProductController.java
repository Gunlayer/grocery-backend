package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.dto.ProductNoDescDTO;
import com.endava.groceryshopservice.entities.dto.ProductWithDescDTO;
import com.endava.groceryshopservice.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "offers REST APIs to work with products")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @ApiOperation(value = "fetches all the products")
    @GetMapping
    List<ProductNoDescDTO> getAll() {
        return productService.getAll()
                .stream()
                .map(ProductNoDescDTO::new)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "gets a product by ID")
    @GetMapping("/{id}")
    ProductWithDescDTO getById(@PathVariable long id) {
        return new ProductWithDescDTO(productService.getById(id));
    }

    @ApiOperation(value = "introduces new product")
    @PostMapping
    ProductWithDescDTO add(@RequestBody Product product) {
        return new ProductWithDescDTO(productService.save(product));
    }

    @ApiOperation(value = "fetches certain number (15 by default) of mostly viewed products")
    @GetMapping("/mostpopular")
    List<ProductNoDescDTO> getMostPopular(@RequestParam(defaultValue = "15") int number) {
        return productService.getMostViewed(number)
                .stream()
                .map(ProductNoDescDTO::new)
                .collect(Collectors.toList());
    }
}