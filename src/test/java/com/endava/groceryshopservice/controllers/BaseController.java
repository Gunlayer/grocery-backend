package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.security.JwtConfigurer;
import com.endava.groceryshopservice.security.JwtTokenProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;


public class BaseController {

    @MockBean
    JwtConfigurer jwtConfigurer;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @MockBean
    AuthenticationManager authenticationManagerBean;

    public static <V> String createExpectedBody(V v) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(v);
    }

    public static <V> String createRequest(V v) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(v);
    }
}