package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.Visitor;
import com.endava.groceryshopservice.entities.dto.MostSoldProductDTO;
import com.endava.groceryshopservice.entities.dto.VisitorRequestDTO;
import java.util.List;

public interface VisitorService {
    void addVisitor(VisitorRequestDTO request);

    void deleteVisitor(String visitorId);

    List<Visitor> findVisitors();
}
