package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.UserInformationDto;
import com.endava.groceryshopservice.exceptions.AlreadyExistingUserException;
import com.endava.groceryshopservice.exceptions.InvalidEmailException;
import com.endava.groceryshopservice.repositories.UserRepository;
import com.endava.groceryshopservice.services.UserService;
import com.endava.groceryshopservice.services.UserValidationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserValidationService userValidationService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<User> getAll(Pageable page) {
        return userRepository.findAll(page);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new InvalidEmailException("Not existing email"));
    }

    @Override
    public User addUser(UserInformationDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new AlreadyExistingUserException("User with the same email already exists");
        }
        User user = UserInformationDto.toUser(dto);
        userValidationService.testUserValidation(user);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    @Override
    public User editUser(UserInformationDto dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new InvalidEmailException("Not existing email"));

        userValidationService.testUserValidation(UserInformationDto.toUser(dto));

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(dto.getRole());
        user.setStatus(dto.getStatus());

        return userRepository.save(user);
    }

    @Override
    public User deleteUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new InvalidEmailException("Not existing email"));

        userRepository.delete(user);

        return user;
    }
}