package com.endava.groceryshopservice.repositories;

import com.endava.groceryshopservice.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}