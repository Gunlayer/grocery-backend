package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.dto.RegistrationResponseDTO;
import com.endava.groceryshopservice.entities.dto.UserRequestDTO;

import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<RegistrationResponseDTO> register(UserRequestDTO requestDTO);
}