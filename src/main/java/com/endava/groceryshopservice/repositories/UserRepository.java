package com.endava.groceryshopservice.repositories;

import com.endava.groceryshopservice.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByRegistrationDateAfter(LocalDate date);

    Page<User> findByEmailContainingIgnoreCase(Pageable page, String email);

    long countByEmailContainingIgnoreCase(String email);
}