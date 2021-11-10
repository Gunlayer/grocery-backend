package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.Cart;
import com.endava.groceryshopservice.repositories.CartRepository;
import com.endava.groceryshopservice.services.impl.CartServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import static com.endava.groceryshopservice.utils.CartUtils.CART;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {
    @Mock
    CartRepository cartRepository;

    @InjectMocks
    CartServiceImpl cartService;

    @Captor
    ArgumentCaptor<Cart> cart;

    @Test
    @WithMockUser
    void shouldCreateCartForUser() {
        when(cartRepository.save(cart.capture())).thenReturn(CART);
        cartService.createCartForUser(USER_ONE);
        assertEquals(CART.getUser(), cart.getValue().getUser());
    }
}