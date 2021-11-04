package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api(value = "Model for authentication request")
@Data
public class AuthenticationRequestDTO {

    @ApiModelProperty(value = "User's email", example = "jorikbarba@gmail.com")
    private String email;

    @ApiModelProperty(value = "User's password", notes = "User's password comes encoded")
    private String password;
}