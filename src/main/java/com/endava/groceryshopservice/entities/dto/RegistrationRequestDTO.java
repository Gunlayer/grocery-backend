package com.endava.groceryshopservice.entities.dto;

import com.endava.groceryshopservice.entities.User;

import com.endava.groceryshopservice.entities.user_permission.Role;
import com.endava.groceryshopservice.entities.user_permission.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDTO {
    private String email;
    private String password;

    public User toUser(){
        User user = User.builder()
                .email(this.email)
                .password(this.password)
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build();
        return user;
    }
}
