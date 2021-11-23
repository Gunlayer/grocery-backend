package com.endava.groceryshopservice.repositories;

import com.endava.groceryshopservice.entities.Views;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewsRepository extends JpaRepository<Views, Long> {
    List<Views> findAllByNumberGreaterThanEqualOrderByNumberDescProduct_nameAsc(long startNumber, Pageable pageable);
}