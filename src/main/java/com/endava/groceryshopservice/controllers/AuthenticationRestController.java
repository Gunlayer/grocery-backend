package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.AuthenticationRequestDTO;
import com.endava.groceryshopservice.exceptions.model.ResponseData;
import com.endava.groceryshopservice.security.JwtTokenProvider;
import com.endava.groceryshopservice.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "Auth controller exposes sign-in and sign-up REST APIs ")
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "authenticates user's request to log in the system")
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userService.getByEmail(request.getEmail());
        String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
        return ResponseEntity.ok(ResponseData.builder().email(request.getEmail()).token(token).build());
    }

    @ApiOperation(value = "sign out the user")
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}