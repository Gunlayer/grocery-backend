package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.dto.RegistrationRequestDTO;
import com.endava.groceryshopservice.exceptions.AlreadyExistingUserException;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<?> register(RegistrationRequestDTO requestDTO) throws AlreadyExistingUserException;
}