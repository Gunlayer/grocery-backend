package com.endava.groceryshopservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDTO {
    @NotBlank(message = "Must not be blank")
//    @Email(message = "Email is not valid", regexp = "^(.+)@(.+\\.)+(.+)$")
    private String email;
    @NotBlank(message = "Must not be blank")
    private String password;
}
