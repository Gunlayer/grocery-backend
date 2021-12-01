package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.Order;
import com.endava.groceryshopservice.entities.dto.CheckoutRequestDTO;

public interface CheckoutService {
    Order save(CheckoutRequestDTO checkoutRequest);
}
