package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product save(Product product);

    List<Product> getMostViewed(int number);

    Product getById(long id);
}
