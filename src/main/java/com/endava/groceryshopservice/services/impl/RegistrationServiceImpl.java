package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.RegistrationRequestDTO;
import com.endava.groceryshopservice.entities.user_permission.Role;
import com.endava.groceryshopservice.entities.user_permission.Status;
import com.endava.groceryshopservice.exceptions.AlreadyExistingUserException;
import com.endava.groceryshopservice.exceptions.model.ResponseData;
import com.endava.groceryshopservice.repositories.UserRepository;
import com.endava.groceryshopservice.security.JwtTokenProvider;
import com.endava.groceryshopservice.services.EmailValidatorService;
import com.endava.groceryshopservice.services.PasswordValidatorService;
import com.endava.groceryshopservice.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final EmailValidatorService emailValidatorService;
    private final PasswordValidatorService passwordValidatorService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity<?> register(RegistrationRequestDTO requestDTO) throws AlreadyExistingUserException {
        emailValidatorService.test(requestDTO.getEmail());
        passwordValidatorService.test(requestDTO.getPassword());

        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new AlreadyExistingUserException("User with the email " + requestDTO.getEmail() + " already exists.");
        }

        User user = User.builder()
                .email(requestDTO.getEmail())
                .password(requestDTO.getPassword())
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build();
        userRepository.save(user);

        String token = jwtTokenProvider.createToken(requestDTO.getEmail(), user.getRole().name());
        return ResponseEntity.ok(ResponseData.builder().email(requestDTO.getEmail()).token(token).build());
    }
}
