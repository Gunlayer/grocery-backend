package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Item;
import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.exceptions.NoProductFoundException;
import com.endava.groceryshopservice.repositories.ItemRepository;
import com.endava.groceryshopservice.repositories.ProductRepository;
import com.endava.groceryshopservice.repositories.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static com.endava.groceryshopservice.utils.ItemUtils.ITEMS_LIST;
import static com.endava.groceryshopservice.utils.ItemUtils.ITEM_ONE;
import static com.endava.groceryshopservice.utils.ItemUtils.ITEM_RESPONSE_DTO;
import static com.endava.groceryshopservice.utils.ItemUtils.ITEM_TO_ADD_REQUEST_DTO;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.SIZE;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {
    @Mock
    ItemRepository itemRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ItemServiceImpl itemService;

    @Captor
    ArgumentCaptor<Item> itemCapture;


    @Test
    void shouldFindUserCart() {
        when(itemRepository.findByUser_Email(USER_EMAIL)).thenReturn(ITEMS_LIST);
        List<ItemResponseDTO> items = itemService.findUserCart(USER_EMAIL);
        assertEquals(items, ITEM_RESPONSE_DTO);
    }

    @Test
    void shouldAddNewItemToCart() {
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(java.util.Optional.ofNullable(USER_ONE));
        when(productRepository.findById(ID_ONE)).thenReturn(java.util.Optional.ofNullable(PRODUCT_ONE));
        when(itemRepository.findByUserAndProductAndSize(USER_ONE, PRODUCT_ONE, SIZE)).thenReturn(null);
        when(itemRepository.save(itemCapture.capture())).thenReturn(itemCapture.capture());

        itemService.addItemToCart(ITEM_TO_ADD_REQUEST_DTO);

        assertEquals(ITEM_ONE, itemCapture.getValue());
    }

    @Test
    void shouldThrowUserNotFoundException() {
        when(userRepository.findByEmail(USER_EMAIL)).
                thenReturn(Optional.empty());

        Exception actualException = assertThrows(UsernameNotFoundException.class, () ->
                itemService.addItemToCart(ITEM_TO_ADD_REQUEST_DTO));
        assertEquals("Could not find user with email " + USER_EMAIL, actualException.getMessage());
    }

    @Test
    void shouldThrowProductNotFoundException() {
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(java.util.Optional.ofNullable(USER_ONE));

        when(productRepository.findById(ID_ONE)).
                thenReturn(Optional.empty());

        Exception actualException = assertThrows(NoProductFoundException.class, () ->
                itemService.addItemToCart(ITEM_TO_ADD_REQUEST_DTO));
        assertEquals("Could not find a product with id " + ID_ONE, actualException.getMessage());
    }
}