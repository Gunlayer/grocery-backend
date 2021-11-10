package com.endava.groceryshopservice.repositories;

import com.endava.groceryshopservice.entities.Views;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewsRepository extends JpaRepository<Views, Long> {
    List<Views> findAllByNumberGreaterThanEqualOrderByNumberDesc(long startNumber, Pageable pageable);
}