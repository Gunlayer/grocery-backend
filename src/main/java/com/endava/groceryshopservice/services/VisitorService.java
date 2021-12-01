package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.dto.VisitorRequestDTO;

public interface VisitorService {
    void addVisitor(VisitorRequestDTO request);

    void deleteVisitor(String visitorId);

    Long findVisitors();
}
