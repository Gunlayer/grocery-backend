package com.endava.groceryshopservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.endava.groceryshopservice.entities.dto.UserRequestDTO;
import com.endava.groceryshopservice.exceptions.model.RegistrationResponseData;
import com.endava.groceryshopservice.services.RegistrationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@Api(value = "Registration controller exposes sign-up REST APIs")
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationRestController {

    private final RegistrationService registrationService;

    @ApiOperation(value = "processes user's request to perform registration into the system")
    @PostMapping
    public ResponseEntity<RegistrationResponseData> register(@RequestBody UserRequestDTO requestDTO) {
        return registrationService.register(requestDTO);
    }
}