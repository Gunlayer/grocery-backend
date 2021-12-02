package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Order;
import com.endava.groceryshopservice.entities.OrderContent;
import com.endava.groceryshopservice.entities.dto.CheckoutRequestDTO;
import com.endava.groceryshopservice.entities.dto.MostSoldProductDTO;
import com.endava.groceryshopservice.repositories.OrderContentRepository;
import com.endava.groceryshopservice.repositories.OrderRepository;
import com.endava.groceryshopservice.repositories.ProductRepository;
import com.endava.groceryshopservice.services.CheckoutService;
import com.endava.groceryshopservice.services.ProductService;
import com.endava.groceryshopservice.utils.ValueComparator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final OrderRepository orderRepository;
    private final OrderContentRepository orderContentRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Override
    @Transactional()
    public Order save(CheckoutRequestDTO req) {
        String fullAddress = req.getFirstName() + " " + req.getLastName() + ", " + req.getAddress() + " " + req.getApartment();
        Order order = Order.builder().email(req.getEmail()).address(fullAddress)
                .price(req.getTotalPrice()).orderDate(LocalDate.now()).build();
        orderRepository.save(order);
        List<OrderContent> productList = req.getOrderList()
                .stream()
                .map((p) -> OrderContent.builder().order(order).product(productRepository.getById(p.getProductId()))
                        .quantity(p.getSize()).size(p.getQuantity()).build())
                .collect(Collectors.toList());
        orderContentRepository.saveAll(productList);
        return order;
    }

    @Override
    public List<Order> findWeeklyOrders() {
        return orderRepository.findByOrderDateAfter(LocalDate.now().minusDays(7));
    }

    @Override
    public List<MostSoldProductDTO> getMostSoldProductResponseDTO() {
        return findMostSoldProduct().entrySet().stream()
                .map(e -> new MostSoldProductDTO(productService.getById(e.getKey()), e.getValue()))
                .collect(Collectors.toList());
    }

    private TreeMap<Long, Integer> findMostSoldProduct() {
        List<OrderContent> orderContentList = findWeeklyOrders().stream()
                .map(Order::getOrderContent)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        Map<Long, Integer> soldProductsMap = new HashMap<>();
        for (OrderContent orderContent : orderContentList) {
            if (soldProductsMap.containsKey(orderContent.getProduct().getId())) {
                soldProductsMap.put(orderContent.getProduct().getId(), soldProductsMap.get(orderContent.getProduct().getId()) + orderContent.getQuantity() * orderContent.getSize());
            } else {
                soldProductsMap.put(orderContent.getProduct().getId(), orderContent.getQuantity() * orderContent.getSize());
            }
        }

        TreeMap<Long, Integer> soldProductsSortedMap = new TreeMap<>(new ValueComparator(soldProductsMap));
        soldProductsSortedMap.putAll(soldProductsMap);

        return soldProductsSortedMap.entrySet().stream()
                .limit(5)
                .collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
    }
}