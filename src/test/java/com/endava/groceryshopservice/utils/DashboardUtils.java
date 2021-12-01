package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.Dashboard;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DashboardUtils {
    public static final Dashboard DASHBOARD = Dashboard.builder()
            .incompleteOrders(2L)
            .newUserRegistrations(2L)
            .uniqueUsers(3L)
            .build();
}
