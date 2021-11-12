package com.endava.groceryshopservice.repositories;

import com.endava.groceryshopservice.entities.Item;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByUser_Email(String email);
    Item findByUserAndProductAndSize(User user, Product product, Integer size);
    @Modifying
    @Transactional
    @Query("update Item set quantity = :newQuantity where id = :id")
    Item updateQuantity(Long id, Integer newQuantity);
}