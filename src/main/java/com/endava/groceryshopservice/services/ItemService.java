package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import java.util.List;

public interface ItemService {
    List<ItemResponseDTO> findUserCard(String email);
}