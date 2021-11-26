package com.endava.groceryshopservice.entities.dto;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.user_permission.Role;
import com.endava.groceryshopservice.entities.user_permission.Status;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserInformationDto {
    private String email;
    private String password;
    private Role role;
    private Status status;

    public static User toUser(UserInformationDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .status(dto.getStatus())
                .build();
    }
}
