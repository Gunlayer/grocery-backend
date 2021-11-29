package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Order;
import com.endava.groceryshopservice.entities.OrderContent;
import com.endava.groceryshopservice.entities.dto.CheckoutRequestDTO;
import com.endava.groceryshopservice.repositories.OrderContentRepository;
import com.endava.groceryshopservice.repositories.OrderRepository;
import com.endava.groceryshopservice.repositories.ProductRepository;
import com.endava.groceryshopservice.services.CheckoutService;
import com.endava.groceryshopservice.services.ItemService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final OrderRepository orderRepository;
    private final OrderContentRepository orderContentRepository;
    private final ProductRepository productRepository;
    private final ItemService itemService;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Order save(CheckoutRequestDTO req) {
        Order order = Order.builder().email(req.getEmail()).address(req.getAddress())
                .price(req.getTotalPrice()).orderDate(LocalDate.now()).build();
        orderRepository.save(order);
        List<OrderContent> productList = req.getOrderList()
                .stream()
                .map((p) -> OrderContent.builder().order(order).product(productRepository.getById(p.getProductId())).quantity(p.getSize()).size(p.getQuantity()).build())
                .collect(Collectors.toList());
        orderContentRepository.saveAll(productList);
        return order;
    }
}
