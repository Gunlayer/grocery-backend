package com.endava.groceryshopservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.AddressDTO;
import com.endava.groceryshopservice.entities.dto.AuthenticationResponseDTO;
import com.endava.groceryshopservice.entities.dto.UserRequestDTO;
import com.endava.groceryshopservice.exceptions.BadCredentialsException;
import com.endava.groceryshopservice.security.JwtTokenProvider;
import com.endava.groceryshopservice.services.AddressService;
import com.endava.groceryshopservice.services.ItemService;
import com.endava.groceryshopservice.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "Auth controller exposes sign-in and sign-up REST APIs ")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ItemService itemService;
    private final AddressService addressService;

    @ApiOperation(value = "authenticates user's request to log in the system")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody UserRequestDTO request) {
        User user;
        try {
            user = userService.getByEmail(request.getEmail());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Incorrect combination of email and/or password");
        }
        itemService.addItems(request);
        String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
        AddressDTO address = new AddressDTO(addressService.findByEmail(user.getEmail()));

        return ResponseEntity.ok(AuthenticationResponseDTO.builder()
                .email(request.getEmail())
                .token(token)
                .address(address)
                .cartItems(itemService.findUserCart(request.getEmail()))
                .build());
    }

    @ApiOperation(value = "signs out the user")
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}