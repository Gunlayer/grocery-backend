package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import com.endava.groceryshopservice.entities.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Api(value = "Address model information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @ApiModelProperty(value = "User's email")
    private String email;

    @ApiModelProperty(value = "User's first name", notes = "optional")
    private String firstName;

    @ApiModelProperty(value = "User's last name")
    private String lastName;

    @ApiModelProperty(value = "User's address")
    private String address;

    @ApiModelProperty(value = "User's apartment", notes = "optional")
    private String apartment;

    public AddressDTO(Address address) {
        this.email = address.getAddressEmail();
        this.firstName = address.getFirstName();
        this.lastName = address.getLastName();
        this.address = address.getAddress();
        this.apartment = address.getApartment();
    }
}