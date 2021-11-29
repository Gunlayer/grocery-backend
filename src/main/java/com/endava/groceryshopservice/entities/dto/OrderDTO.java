package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import com.endava.groceryshopservice.entities.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Api(value = "Order model information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @ApiModelProperty(value = "Customer's email address")
    private String email;

    @ApiModelProperty(value = "Customer's shipping address")
    private String address;

    @ApiModelProperty(value = "Order's price")
    private Double price;

    @ApiModelProperty(value = "Order's date")
    private LocalDate localDate;

    public OrderDTO(Order order) {
        this.email = order.getEmail();
        this.address = order.getAddress();
        this.price = order.getPrice();
        this.localDate = order.getOrderDate();
    }
}
