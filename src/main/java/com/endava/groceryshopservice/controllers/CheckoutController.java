package com.endava.groceryshopservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.endava.groceryshopservice.entities.dto.CheckoutRequestDTO;
import com.endava.groceryshopservice.security.JwtTokenProvider;
import com.endava.groceryshopservice.services.AddressService;
import com.endava.groceryshopservice.services.CheckoutService;
import com.endava.groceryshopservice.services.ItemService;
import com.endava.groceryshopservice.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final AddressService addressService;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;
    private final CheckoutService checkoutService;
    private final ItemService itemService;

    @ApiOperation(value = "finishes order checkout",
            notes = " Also removes cart items")
    @PostMapping
    @ResponseBody
    public ResponseEntity<CheckoutRequestDTO> saveAddressAtCheckout(@RequestBody CheckoutRequestDTO requestDTO, @RequestHeader Map<String, String> headers) {

//        if (requestDTO.isNeedToSave()) {
//            String userEmail = tokenProvider.getUsername(headers.get("authorization"));
//            User user = userService.getByEmail(userEmail);
//            Address address = requestDTO.toAddress();
//            address.setUser(user);
//            addressService.save(address);
//        }


        String email;
        if ((email = tokenProvider.getUsername(headers.get("authorization"))) != null) {
            itemService.deleteAllByEmail(email);
        }
        checkoutService.save(requestDTO);
        return new ResponseEntity<>(requestDTO, HttpStatus.CREATED);
    }
}