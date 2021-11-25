package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.RegistrationResponseDTO;
import com.endava.groceryshopservice.entities.dto.UserRequestDTO;
import com.endava.groceryshopservice.exceptions.AlreadyExistingUserException;
import com.endava.groceryshopservice.repositories.UserRepository;
import com.endava.groceryshopservice.security.JwtTokenProvider;
import com.endava.groceryshopservice.services.ItemService;
import com.endava.groceryshopservice.services.RegistrationService;
import com.endava.groceryshopservice.services.RegistrationValidationService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    @Value("${jwt.strength}")
    private int strength;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final ItemService itemService;
    private final RegistrationValidationService registrationValidationService;

    @Override
    public ResponseEntity<RegistrationResponseDTO> register(UserRequestDTO requestDTO) throws AlreadyExistingUserException {
        registrationValidationService.testEmailValidation(requestDTO.getEmail());
        registrationValidationService.testPasswordValidation(requestDTO.getPassword());

        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new AlreadyExistingUserException("A user with the same email already exists");
        }
        User user = requestDTO.toUser();
        String encodedPassword = new BCryptPasswordEncoder(strength).encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        itemService.addItems(requestDTO);

        String token = jwtTokenProvider.createToken(requestDTO.getEmail(), user.getRole().name());
        return ResponseEntity.ok(RegistrationResponseDTO.builder().email(requestDTO.getEmail()).token(token).build());
    }
}