package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.dto.UserRequestDTO;
import com.endava.groceryshopservice.exceptions.model.RegistrationResponseData;

import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<RegistrationResponseData> register(UserRequestDTO requestDTO) ;
}