package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Order;
import com.endava.groceryshopservice.entities.dto.MostSoldProductDTO;
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
import java.util.List;

import static com.endava.groceryshopservice.utils.CheckoutRequestUtils.CHECKOUT_REQUEST_TWO;
import static com.endava.groceryshopservice.utils.MostSoldProductUtils.MOST_SOLD_PRODUCT_DTO_LIST;
import static com.endava.groceryshopservice.utils.OrderUtils.ORDER_LIST;
import static com.endava.groceryshopservice.utils.OrderUtils.ORDER;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_THREE;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.ID_FIVE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_THREE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @Mock
    ProductServiceImpl productService;
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

        assertThat(ORDER).isEqualTo(expectedOrder);
        verify(orderRepository, times(1)).save(orderCapture.capture());
        verify(productRepository, times(1)).getById(ID_FIVE);
    }

    @Test
    void shouldFindNewOrders() {
        when(orderRepository.findByOrderDateAfter(LocalDate.now().minusDays(7))).thenReturn(ORDER_LIST);

        List<Order> orderList = checkoutService.findWeeklyOrders();

        assertEquals(orderList, ORDER_LIST);
    }

    @Test
    void shouldGetMostSoldProductResponseDTO() {
        when(orderRepository.findByOrderDateAfter(LocalDate.now().minusDays(7))).thenReturn(ORDER_LIST);
        when(productService.getById(ID_TWO)).thenReturn(PRODUCT_TWO);
        when(productService.getById(ID_THREE)).thenReturn(PRODUCT_THREE);

        List<MostSoldProductDTO> actual = checkoutService.getMostSoldProductResponseDTO();

        assertEquals(actual, MOST_SOLD_PRODUCT_DTO_LIST);
    }
}