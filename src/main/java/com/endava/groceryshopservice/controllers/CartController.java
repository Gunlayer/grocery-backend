package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.services.ItemService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final ItemService itemService;

    @GetMapping("/{userEmail}")
    public List<ItemResponseDTO> getUserCart(@PathVariable("userEmail") String userEmail) {
        return itemService.findUserCart(userEmail);
    }
}