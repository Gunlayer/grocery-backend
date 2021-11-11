package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.repositories.ItemRepository;
import com.endava.groceryshopservice.services.impl.ItemServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.endava.groceryshopservice.utils.ItemUtils.ITEMS_LIST;
import static com.endava.groceryshopservice.utils.ItemUtils.ITEM_RESPONSE_DTO;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {
    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemServiceImpl itemService;

    @Test
    void shouldFindUserCart() {
        when(itemRepository.findByUser_Email(USER_EMAIL)).thenReturn(ITEMS_LIST);
        List<ItemResponseDTO> items = itemService.findUserCart(USER_EMAIL);
        assertEquals(items, ITEM_RESPONSE_DTO);
    }
}