package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.Address;

public interface AddressService {
    Address findByEmail(String email);

    Address save(Address address);
}