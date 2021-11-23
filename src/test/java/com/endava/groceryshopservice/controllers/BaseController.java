package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.security.JwtConfigurer;
import com.endava.groceryshopservice.security.JwtTokenProvider;
import com.endava.groceryshopservice.security.SecurityUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.ServletException;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BaseController {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected JwtConfigurer jwtConfigurer;

    @MockBean
    protected JwtTokenProvider tokenProvider;

    @MockBean
    protected AuthenticationManager authenticationManagerBean;

    private final ObjectMapper objectMapper = new ObjectMapper();

    protected void prepareAuthorizedRequestForUser(User user, String token) throws ServletException, IOException {
        when(tokenProvider.resolveToken(any())).thenReturn(token);
        when(tokenProvider.validateToken(token)).thenReturn(true);
        when(tokenProvider.getAuthentication(token))
                .thenReturn(new UsernamePasswordAuthenticationToken(
                        SecurityUser.fromUser(user), "", SecurityUser.fromUser(user).getAuthorities())
                );
        when(tokenProvider.getUsername(token)).thenReturn(user.getEmail());
    }

    protected <V> String createJsonString(V v) throws JsonProcessingException {
        return objectMapper.writeValueAsString(v);
    }
}