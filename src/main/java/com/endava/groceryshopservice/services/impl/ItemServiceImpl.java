package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Item;
import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.repositories.ItemRepository;
import com.endava.groceryshopservice.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public List<ItemResponseDTO> findUserCard(String email) {
        List<Item> items = itemRepository.findByCart_UserEmail(email);
        List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();
        for (Item item : items) {
            ItemResponseDTO itemResponseDTO = item.toItemResponseDTO();
            itemResponseDTOList.add(itemResponseDTO);
        }
        return itemResponseDTOList;
    }
}