package com.endava.groceryshopservice.repositories;

import com.endava.groceryshopservice.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCart_UserEmail(String email);
}