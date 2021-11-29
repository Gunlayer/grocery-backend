package com.endava.groceryshopservice.entities.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Api(value = "Visitor model for add and delete requests")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitorRequestDTO {
    @ApiModelProperty(value = "Visitor's id")
    private String visitorId;
}
