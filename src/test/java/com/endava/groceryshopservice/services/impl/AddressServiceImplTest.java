package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.repositories.AddressRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.endava.groceryshopservice.utils.AddressUtils.ADDRESS_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    AddressServiceImpl addressService;

    @Test
    void findByEmail() {
        when(addressRepository.findById(USER_EMAIL)).thenReturn(Optional.of(ADDRESS_ONE));

        assertThat(addressService.findByEmail(USER_EMAIL)).isEqualTo(ADDRESS_ONE);
    }
}