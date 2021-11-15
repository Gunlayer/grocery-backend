package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.dto.RegistrationRequestDTO;
import com.endava.groceryshopservice.exceptions.AlreadyExistingUserException;
import com.endava.groceryshopservice.exceptions.model.RegistrationResponseData;

import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<RegistrationResponseData> register(RegistrationRequestDTO requestDTO);
}