package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Item;
import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.repositories.ItemRepository;
import com.endava.groceryshopservice.services.ItemService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public List<ItemResponseDTO> findUserCard(String email) {
        return itemRepository.findByUser_Email(email)
                .stream()
                .map(Item::toItemResponseDTO)
                .collect(Collectors.toList());
    }
}