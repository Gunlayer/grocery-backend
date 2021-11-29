package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.repositories.OrderContentRepository;
import com.endava.groceryshopservice.repositories.OrderRepository;
import com.endava.groceryshopservice.repositories.ProductRepository;
import com.endava.groceryshopservice.services.ItemService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.endava.groceryshopservice.utils.OrderContentUtil.ORDER_CONTENT;
import static com.endava.groceryshopservice.utils.OrderUtils.ORDER_ONE;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.ID_FIVE;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckoutServiceImplTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderContentRepository orderContentRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    ItemService itemService;

    @InjectMocks
    CheckoutServiceImpl checkoutService;

    @Test
    void save() {
        when(orderRepository.save(ORDER_ONE)).thenReturn(ORDER_ONE);
        when(orderContentRepository.saveAll(List.of(ORDER_CONTENT))).thenReturn(List.of(ORDER_CONTENT));
        when(productRepository.getById(ID_FIVE)).thenReturn(PRODUCT_TWO);


//        assertThat((checkoutService.save()))
    }


}