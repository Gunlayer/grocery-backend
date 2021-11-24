package com.endava.groceryshopservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

@Api(value = "Cart controller adds functionality to manipulate items")
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final ItemService itemService;

    @ApiOperation(value = "fetches cart for specific user")
    @GetMapping("/{userEmail}")
    @PreAuthorize("hasAuthority('users:read')")
    public List<ItemResponseDTO> getUserCart(@PathVariable("userEmail") String userEmail) {
        return itemService.findUserCart(userEmail);
    }

    @ApiOperation(value = "introduces new item")
    @PostMapping()
    @PreAuthorize("hasAuthority('users:addInBag')")
    public void addItemToCart(@RequestBody ItemToAddDeleteRequestDTO requestDTO) {
        itemService.addItemToCart(requestDTO);
    }

    @ApiOperation(value = "delete item")
    @DeleteMapping
    @PreAuthorize("hasAuthority('users:addInBag')")
    public void deleteItem(@RequestBody ItemToAddDeleteRequestDTO item){
        itemService.deleteItem(item);
    }
}