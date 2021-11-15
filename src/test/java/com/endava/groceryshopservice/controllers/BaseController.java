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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BaseController {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected JwtConfigurer jwtConfigurer;

    @MockBean
    protected JwtTokenProvider jwtTokenProvider;

    @MockBean
    protected AuthenticationManager authenticationManagerBean;

    private final ObjectMapper objectMapper = new ObjectMapper();

    protected void prepareAuthorizedRequestForUser(User user, String token) {
        when(jwtTokenProvider.resolveToken(any())).thenReturn(token);
        when(jwtTokenProvider.validateToken(token)).thenReturn(true);
        when(jwtTokenProvider.getAuthentication(token))
                .thenReturn(new UsernamePasswordAuthenticationToken(
                        SecurityUser.fromUser(user), "", SecurityUser.fromUser(user).getAuthorities())
                );
    }

    protected <V> String createExpectedBody(V v) throws JsonProcessingException {
        return objectMapper.writeValueAsString(v);
    }

    protected <V> String createRequest(V v) throws JsonProcessingException {
        return objectMapper.writeValueAsString(v);
    }
}