package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Api(value = "User information model")
@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponseDTO {

    @ApiModelProperty(value = "User's email")
    private String email;

    @ApiModelProperty(value = "User's auth token")
    private String token;

    @ApiModelProperty(value = "User's saved address", notes = "might not exist")
    private AddressDTO address;

    @ApiModelProperty(value = "User's cart")
    private List<ItemResponseDTO> cartItems;
}