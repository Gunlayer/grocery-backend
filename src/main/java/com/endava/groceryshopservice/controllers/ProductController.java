package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.dto.ProductNoDescDTO;
import com.endava.groceryshopservice.entities.dto.ProductWithDescDTO;
import com.endava.groceryshopservice.services.ProductService;

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

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    List<ProductNoDescDTO> getAll() {
        return productService.getAll()
                .stream()
                .map(ProductNoDescDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    ProductWithDescDTO getById(@PathVariable long id) {
        return new ProductWithDescDTO(productService.getById(id));
    }

    @PostMapping
    ProductWithDescDTO add(@RequestBody Product product) {
        return new ProductWithDescDTO(productService.save(product));
    }

    @GetMapping("/mostpopular")
    List<ProductNoDescDTO> getMostPopular(@RequestParam(defaultValue = "15") int number) {
        return productService.getMostViewed(number)
                .stream()
                .map(ProductNoDescDTO::new)
                .collect(Collectors.toList());
    }
}