package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.Item;
import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.entities.dto.ItemToAddDeleteRequestDTO;
import com.endava.groceryshopservice.entities.dto.UserRequestDTO;

import java.util.List;

public interface ItemService {
    List<ItemResponseDTO> findUserCart(String email);

    void addItemToCart(ItemToAddDeleteRequestDTO requestDTO);

    void addItems(UserRequestDTO requestDTO);

    void deleteItem(ItemToAddDeleteRequestDTO requestDTO);

    Item updateItem(Item item, ItemToAddDeleteRequestDTO requestDTO);
}