package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.Order;
import com.endava.groceryshopservice.entities.dto.CheckoutRequestDTO;
import com.endava.groceryshopservice.entities.dto.MostSoldProductDTO;

import java.util.List;

public interface CheckoutService {
    Order save(CheckoutRequestDTO checkoutRequest);

    List<Order> findWeeklyOrders();

    List<MostSoldProductDTO> getMostSoldProductResponseDTO();
}