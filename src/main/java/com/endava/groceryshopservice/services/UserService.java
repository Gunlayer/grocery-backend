package com.endava.groceryshopservice.services;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.UserInformationDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {
    User save(User user);

    User getByEmail(String email);

    User addUser(UserInformationDto user);

    User editUser(UserInformationDto user);

    User deleteUser(String userEmail);

    List<User> findNewRegisteredUsers();

    Page<User> getAll(Pageable page, String email);

    long getCountOfUsersWithEmail(String email);
}