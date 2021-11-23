package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.entities.dto.ItemToAddDeleteRequestDTO;
import com.endava.groceryshopservice.services.ItemService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PreAuthorize("hasAuthority('users:read')")
    public List<ItemResponseDTO> getUserCart(@PathVariable("userEmail") String userEmail) {
        return itemService.findUserCart(userEmail);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('users:read')")
    public void addItemToCart(@RequestBody ItemToAddDeleteRequestDTO requestDTO) {
        itemService.addItemToCart(requestDTO);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('users:read')")
    public void deleteItem(@RequestBody ItemToAddDeleteRequestDTO deleteItemRequestDTO){
        itemService.deleteItem(deleteItemRequestDTO);
    }
}