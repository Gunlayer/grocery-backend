package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Item;
import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.exceptions.InvalidQuantityException;
import com.endava.groceryshopservice.exceptions.NoItemFoundException;
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

import java.util.List;
import java.util.Optional;

import static com.endava.groceryshopservice.utils.ItemUtils.ITEMS_LIST;
import static com.endava.groceryshopservice.utils.ItemUtils.ITEM_ONE;
import static com.endava.groceryshopservice.utils.ItemUtils.ITEM_RESPONSE_DTO;
import static com.endava.groceryshopservice.utils.ItemUtils.ITEM_TO_ADD_DELETE_REQUEST_DTO;
import static com.endava.groceryshopservice.utils.ItemUtils.ITEM_TO_DELETE_REQUEST_DTO;
import static com.endava.groceryshopservice.utils.ItemUtils.ITEM_TWO;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.ITEM_NOT_FOUND_EXCEPTION;
import static com.endava.groceryshopservice.utils.TestConstants.PRODUCT_NOT_FOUND_EXCEPTION;
import static com.endava.groceryshopservice.utils.TestConstants.QUANTITY_EXCEPTION;
import static com.endava.groceryshopservice.utils.TestConstants.SIZE;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {
    @Mock
    ItemRepository itemRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    UserServiceImpl userService;

    @Mock
    ProductServiceImpl productService;

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
        when(userRepository.getByEmail(USER_EMAIL)).thenReturn(USER_ONE);
        when(productRepository.findById(ID_ONE)).thenReturn(java.util.Optional.ofNullable(PRODUCT_ONE));
        when(itemRepository.findByUserAndProductAndSize(USER_ONE, PRODUCT_ONE, SIZE)).thenReturn(null);
        when(itemRepository.save(itemCapture.capture())).thenReturn(itemCapture.capture());

        itemService.addItemToCart(ITEM_TO_ADD_DELETE_REQUEST_DTO);

        assertEquals(ITEM_ONE, itemCapture.getValue());
    }

    @Test
    void shouldThrowProductNotFoundException() {
        when(userRepository.getByEmail(USER_EMAIL)).thenReturn(USER_ONE);

        when(productRepository.findById(ID_ONE)).
                thenReturn(Optional.empty());

        Exception actualException = assertThrows(NoProductFoundException.class, () ->
                itemService.addItemToCart(ITEM_TO_ADD_DELETE_REQUEST_DTO));
        assertEquals(PRODUCT_NOT_FOUND_EXCEPTION, actualException.getMessage());
    }

    @Test
    void shouldDeleteItem() {
        when(userService.getByEmail(USER_EMAIL)).thenReturn(USER_ONE);
        when(productService.getById(ID_ONE)).thenReturn(PRODUCT_ONE);
        when(itemRepository.findByUserAndProductAndSize(USER_ONE, PRODUCT_ONE, SIZE)).thenReturn(ITEM_ONE);
        doNothing().when(itemRepository).delete(itemCapture.capture());

        itemService.deleteItem(ITEM_TO_ADD_DELETE_REQUEST_DTO);

        assertEquals(ITEM_ONE, itemCapture.getValue());
    }

    @Test
    void shouldThrowItemNotFoundException() {
        when(userService.getByEmail(USER_EMAIL)).thenReturn(USER_ONE);
        when(productService.getById(ID_ONE)).thenReturn(PRODUCT_ONE);
        when(itemRepository.findByUserAndProductAndSize(USER_ONE, PRODUCT_ONE, SIZE)).thenReturn(null);

        Exception actualException = assertThrows(NoItemFoundException.class, () ->
                itemService.deleteItem(ITEM_TO_ADD_DELETE_REQUEST_DTO));
        assertEquals(ITEM_NOT_FOUND_EXCEPTION, actualException.getMessage());
    }

    @Test
    void shouldUpdateQuantity() {
        when(userService.getByEmail(USER_EMAIL)).thenReturn(USER_ONE);
        when(productService.getById(ID_ONE)).thenReturn(PRODUCT_ONE);
        when(itemRepository.findByUserAndProductAndSize(USER_ONE, PRODUCT_ONE, SIZE)).thenReturn(ITEM_TWO);
        when(itemRepository.save(itemCapture.capture())).thenReturn(itemCapture.capture());

        itemService.deleteItem(ITEM_TO_ADD_DELETE_REQUEST_DTO);

        assertEquals(ITEM_TWO, itemCapture.getValue());
    }

    @Test
    void shouldThrowInvalidQuantityException() {
        when(userService.getByEmail(USER_EMAIL)).thenReturn(USER_ONE);
        when(productService.getById(ID_ONE)).thenReturn(PRODUCT_ONE);
        when(itemRepository.findByUserAndProductAndSize(USER_ONE, PRODUCT_ONE, SIZE)).thenReturn(ITEM_ONE);

        Exception actualException = assertThrows(InvalidQuantityException.class, () ->
                itemService.deleteItem(ITEM_TO_DELETE_REQUEST_DTO));
        assertEquals(QUANTITY_EXCEPTION, actualException.getMessage());
    }
}