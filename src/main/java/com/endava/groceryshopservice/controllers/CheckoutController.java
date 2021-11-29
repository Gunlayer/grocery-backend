package com.endava.groceryshopservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.endava.groceryshopservice.entities.Address;
import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.CheckoutRequestDTO;
import com.endava.groceryshopservice.security.JwtTokenProvider;
import com.endava.groceryshopservice.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Api(value = "Rest controller to proceed order checkout")
@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    @ApiOperation(value = "finishes order checkout",
            notes = "currently, only 'save address' feature is implemented")
    @PostMapping
    @ResponseBody
    public void saveAddressAtCheckout(@RequestBody CheckoutRequestDTO requestDTO, @RequestHeader Map<String, String> headers) {
        if (requestDTO.isNeedToSave()) {
            String userEmail = tokenProvider.getUsername(headers.get("authorization"));
            User user = userService.getByEmail(userEmail);
            Address address = requestDTO.toAddress();
            address.setUser(user);
            user.setAddress(address);
            userService.save(user);
        }
    }
}