package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Dashboard;
import com.endava.groceryshopservice.services.CheckoutService;
import com.endava.groceryshopservice.services.DashboardService;
import com.endava.groceryshopservice.services.ItemService;
import com.endava.groceryshopservice.services.UserService;
import com.endava.groceryshopservice.services.VisitorService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImp implements DashboardService {
    private final UserService userService;
    private final ItemService itemService;
    private final VisitorService visitorService;
    private final CheckoutService checkoutService;

    @Override
    public Dashboard getWeeklyDashboard(){
        return Dashboard.builder()
                .newUserRegistrations((long) userService.findNewRegisteredUsers().size())
                .incompleteOrders(itemService.findIncompleteOrders())
                .mostSoldProducts(checkoutService.getMostSoldProductResponseDTO())
                .newOrders((long)checkoutService.findWeeklyOrders().size())
                .uniqueUsers(visitorService.findVisitors())
                .build();
    }
}