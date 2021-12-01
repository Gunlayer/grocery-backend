package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Visitor;
import com.endava.groceryshopservice.entities.dto.VisitorRequestDTO;
import com.endava.groceryshopservice.repositories.VisitorRepository;
import com.endava.groceryshopservice.services.VisitorService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {
    private final VisitorRepository visitorRepository;

    @Override
    public  void addVisitor(VisitorRequestDTO request) {
        Visitor existingVisitor = visitorRepository.findByVisitorId(request.getVisitorId());
        if (existingVisitor == null) {
            Visitor visitor = Visitor.builder()
                    .visitorId(request.getVisitorId())
                    .addingDate(LocalDate.now())
                    .build();
            visitorRepository.save(visitor);
        }
    }

    @Override
    public void deleteVisitor(String visitorId) {
        Visitor existingVisitor = visitorRepository.findByVisitorId(visitorId);
        if (existingVisitor != null)
         visitorRepository.delete(existingVisitor);
    }

    @Override
    public Long findVisitors() {
        return visitorRepository.findByAddingDateAfter(LocalDate.now().minusDays(7)).stream()
                .map(Visitor::getVisitorId)
                .distinct()
                .count();    }
}