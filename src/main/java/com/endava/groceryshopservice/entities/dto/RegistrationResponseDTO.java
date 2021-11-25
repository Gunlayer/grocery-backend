package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Api("User's registration model data")
@Data
@Builder
@AllArgsConstructor
public class RegistrationResponseDTO {

    @ApiModelProperty(value = "User's email")
    private String email;

    @ApiModelProperty(value = "User's auth token")
    private String token;
}
