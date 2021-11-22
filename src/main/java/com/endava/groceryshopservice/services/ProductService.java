package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> getAll(Pageable page);

    Product save(Product product);

    List<Product> getMostViewed(int number);

    Product getById(long id);

    void setRatingForProduct(long productId);
}