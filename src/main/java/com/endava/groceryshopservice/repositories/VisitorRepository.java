package com.endava.groceryshopservice.repositories;

import com.endava.groceryshopservice.entities.Visitor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, String> {
    Visitor findByVisitorId(String visitor);

    List<Visitor> findByAddingDateAfter(LocalDate date);
}
