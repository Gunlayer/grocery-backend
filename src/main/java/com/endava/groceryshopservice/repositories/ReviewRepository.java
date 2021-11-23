package com.endava.groceryshopservice.repositories;

import com.endava.groceryshopservice.entities.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByProductId(long id);
}