package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Views;
import com.endava.groceryshopservice.repositories.ViewsRepository;
import com.endava.groceryshopservice.services.ViewsService;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewsServiceImpl implements ViewsService {
    private final ViewsRepository viewsRepository;

    public List<Views> getMostViewed(int number) {
        if (number <= 0)
            throw new IllegalArgumentException("Number of products must be at least 1");
        return viewsRepository.findAllByNumberGreaterThanEqualOrderByNumberDesc(1L, PageRequest.of(0, number));
    }

    @Override
    public void increaseViewNumber(Views views) {
        views.setNumber(views.getNumber() + 1);
        viewsRepository.save(views);
    }
}