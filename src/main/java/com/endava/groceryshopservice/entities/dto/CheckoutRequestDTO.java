package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import com.endava.groceryshopservice.entities.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Api(value = "Checkout model information")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequestDTO {

    @ApiModelProperty(value = "Checks if address shall be linked to user")
    private boolean needToSave;

    @ApiModelProperty(value = "Order's total price")
    private double totalPrice;

    @ApiModelProperty(value = "Email of user, who is checking out")
    private String email;

    @ApiModelProperty(value = "First name of user, who is checking out", notes = "optional")
    private String firstName;

    @ApiModelProperty(value = "Last name of user, who is checking out")
    private String lastName;

    @ApiModelProperty(value = "User's address")
    private String address;

    @ApiModelProperty(value = "User's apartment", notes = "optional")
    private String apartment;

    @ApiModelProperty(value = "Order's product list")
    private List<ProductForOrderContentDTO> orderList;

    public Address toAddress() {
        return Address.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .apartment(apartment)
                .build();
    }
}