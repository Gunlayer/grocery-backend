package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.services.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("{id}")
    Product getById(@PathVariable long id) {
        return productService.getById(id);
    }

    @PostMapping
    Product add(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("mostpopular")
    List<Product> getMostPopular(@RequestParam(defaultValue = "15") int number) {
        return productService.getMostViewed(number);
    }
}
