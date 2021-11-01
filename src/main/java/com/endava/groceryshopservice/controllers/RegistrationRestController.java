package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.dto.RegistrationRequestDTO;
import com.endava.groceryshopservice.exceptions.AlreadyExistingUserException;
import com.endava.groceryshopservice.services.RegistrationService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationRestController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegistrationRequestDTO requestDTO) throws AlreadyExistingUserException {
        return registrationService.register(requestDTO);
    }
}
