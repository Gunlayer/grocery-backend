package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.Views;

import java.util.List;

public interface ViewsService {
    List<Views> getMostViewed(int number);

    void increaseViewNumber(Views views);
}