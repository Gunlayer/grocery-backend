package com.endava.groceryshopservice.entities.dto;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.user_permission.Role;
import com.endava.groceryshopservice.entities.user_permission.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Api(value = "Model for registration request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDTO {

    @ApiModelProperty(value = "User's email", example = "jorikbarba@gmail.com")
    private String email;

    @ApiModelProperty(value = "User's password", notes = "User's password comes encoded")
    private String password;

    public User toUser() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build();
    }
}
