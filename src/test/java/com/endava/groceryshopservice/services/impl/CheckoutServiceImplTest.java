package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Order;
import com.endava.groceryshopservice.repositories.OrderContentRepository;
import com.endava.groceryshopservice.repositories.OrderRepository;
import com.endava.groceryshopservice.repositories.ProductRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.endava.groceryshopservice.utils.CheckoutRequestUtils.CHECKOUT_REQUEST_TWO;
import static com.endava.groceryshopservice.utils.OrderUtils.ORDER_ONE;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.ID_FIVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckoutServiceImplTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderContentRepository orderContentRepository;
    @Mock
    ProductRepository productRepository;
    @Captor
    ArgumentCaptor<Order> orderCapture;
    @InjectMocks
    CheckoutServiceImpl checkoutService;

    @Test
    void save() {
        String fullAddress = CHECKOUT_REQUEST_TWO.getFirstName() + " " + CHECKOUT_REQUEST_TWO.getLastName() + ", " +
                CHECKOUT_REQUEST_TWO.getAddress() + " " + CHECKOUT_REQUEST_TWO.getApartment();
        Order expectedOrder = Order.builder().id(5L).email("order.email@endava.com").address(fullAddress)
                .price(20.0).orderDate(LocalDate.now()).build();
        when(orderRepository.save(orderCapture.capture())).thenReturn(orderCapture.capture());
        when(productRepository.getById(ID_FIVE)).thenReturn(PRODUCT_TWO);

        checkoutService.save(CHECKOUT_REQUEST_TWO);

        assertThat(ORDER_ONE).isEqualTo(expectedOrder);
        verify(orderRepository, times(1)).save(orderCapture.capture());
        verify(productRepository, times(1)).getById(ID_FIVE);
    }
}