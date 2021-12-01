package com.endava.groceryshopservice.entities;

import com.endava.groceryshopservice.entities.dto.MostSoldProductDTO;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Dashboard {
    private Long newUserRegistrations;
    private Long uniqueUsers;
    private Long newOrders;
    private Long incompleteOrders;
    private List<MostSoldProductDTO> mostSoldProducts;
}