package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.RegistrationRequestDTO;
import com.endava.groceryshopservice.exceptions.AlreadyExistingUserException;
import com.endava.groceryshopservice.exceptions.model.ResponseData;
import com.endava.groceryshopservice.repositories.UserRepository;
import com.endava.groceryshopservice.security.JwtTokenProvider;
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
    private final RegistrationValidationService registrationValidationService;
    private final CartServiceImpl cartService;

    @Override
    public ResponseEntity<?> register(RegistrationRequestDTO requestDTO) throws AlreadyExistingUserException {
        registrationValidationService.testEmailValidation(requestDTO.getEmail());
        registrationValidationService.testPasswordValidation(requestDTO.getPassword());

        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new AlreadyExistingUserException("Invalid email");
        }

        User user = requestDTO.toUser();
        String encodedPassword = new BCryptPasswordEncoder(strength).encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        cartService.createCartForUser(savedUser);

        String token = jwtTokenProvider.createToken(requestDTO.getEmail(), user.getRole().name());
        return ResponseEntity.ok(ResponseData.builder().email(requestDTO.getEmail()).token(token).build());
    }
}