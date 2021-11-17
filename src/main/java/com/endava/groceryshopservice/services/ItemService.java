package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.entities.dto.ItemToAddRequestDTO;
import com.endava.groceryshopservice.entities.dto.UserRequestDTO;

import java.util.List;

public interface ItemService {
    List<ItemResponseDTO> findUserCart(String email);
    void addItemToCart(ItemToAddRequestDTO requestDTO);
    void addItems(UserRequestDTO requestDTO);
}