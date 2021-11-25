package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.entities.dto.ItemToAddDeleteRequestDTO;
import com.endava.groceryshopservice.services.ItemService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Api(value = "Cart controller adds functionality to manipulate items")
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final ItemService itemService;
    private final JwtTokenProvider tokenProvider;

    @ApiOperation(value = "fetches cart for specific user")
    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<ItemResponseDTO> getUserCart(@RequestHeader HashMap<String, String> headers) {
        String userEmail = tokenProvider.getUsername(headers.get("authorization"));
        return itemService.findUserCart(userEmail);
    }

    @ApiOperation(value = "introduces new item")
    @PostMapping()
    @PreAuthorize("hasAuthority('users:addInBag')")
    public ResponseEntity<HttpStatus> addItemToCart(@RequestBody ItemToAddDeleteRequestDTO requestDTO) {
        itemService.addItemToCart(requestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "delete item")
    @DeleteMapping
    @PreAuthorize("hasAuthority('users:addInBag')")
    public ResponseEntity<HttpStatus> deleteItem(@RequestBody ItemToAddDeleteRequestDTO item){
        itemService.deleteItem(item);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}