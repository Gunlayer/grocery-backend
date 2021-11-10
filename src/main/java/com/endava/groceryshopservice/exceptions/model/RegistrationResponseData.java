package com.endava.groceryshopservice.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegistrationResponseData {
    private String email;
    private String token;
}
