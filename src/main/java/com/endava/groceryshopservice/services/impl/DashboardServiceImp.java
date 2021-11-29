package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Dashboard;
import com.endava.groceryshopservice.entities.Item;
import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.Visitor;
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

    @Override
    public Dashboard getWeeklyDashboard(){
        return Dashboard.builder()
                .newUserRegistrations((long) userService.findNewRegisteredUsers().size())
                .incompleteOrders(itemService.findIncompleteOrders().stream().map(Item::getUser).map(User::getEmail).distinct().count())
//                .mostSoldProducts(null)
//                .newOrders(0L)
                .uniqueUsers(visitorService.findVisitors().stream().map(Visitor::getVisitorId).distinct().count())
                .build();
    }
}
