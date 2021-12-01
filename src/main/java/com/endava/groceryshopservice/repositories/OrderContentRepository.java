package com.endava.groceryshopservice.repositories;

import com.endava.groceryshopservice.entities.OrderContent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderContentRepository extends JpaRepository<OrderContent, Long> {
}
