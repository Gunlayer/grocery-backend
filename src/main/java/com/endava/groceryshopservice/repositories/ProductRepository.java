package com.endava.groceryshopservice.repositories;

import com.endava.groceryshopservice.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional(Transactional.TxType.REQUIRED)
    @Modifying
    @Query("UPDATE Product SET rating  = (" +
            "SELECT AVG(rating) FROM Review WHERE product_id = :productId GROUP BY product_id" +
            ") WHERE id = :productId")
    void setRatingForProduct(long productId);

    Page<Product> findByNameContainsIgnoreCase(String name, Pageable pageable);

    long countByNameContainsIgnoreCase(String name);
}