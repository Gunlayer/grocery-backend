package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Address;
import com.endava.groceryshopservice.repositories.AddressRepository;
import com.endava.groceryshopservice.services.AddressService;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public Address findByEmail(String email) {
        return addressRepository.findById(email).orElse(new Address());
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}