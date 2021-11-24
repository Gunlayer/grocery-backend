package com.endava.groceryshopservice.repositories;

import com.endava.groceryshopservice.entities.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
}